#!/bin/bash -x

_environment=${ENVIRONMENT}

#### EDIT BELOW THIS LINE ####
UPSTREAM_BUILD_NUMBER="${COPYARTIFACT_BUILD_NUMBER_UPSTREAM}"
artifactVersion=${ARTIFACT_VERSION}
buildNumber="${UPSTREAM_BUILD_NUMBER}"
containerPort=8000
cpu=1024
asgDesiredCapacity=${DESIRED_CLUSTER_CAPACITY}
asgMaxSize=${DESIRED_CLUSTER_CAPACITY}
asgMinSize=0
datadogEnabled=no
environment=${_environment}
hostPort=8080
imageId="ami-6bb2d67c"
instanceType="${INSTANCE_TYPE}"
keyName="SmartSvcDevOps"
memory="${CONTAINER_MEMORY}"
gitwatcherMemory="${CONTAINER_GITWATCHER_MEMORY}"
serviceCapacity=${DESIRED_SERVICE_CAPACITY}
sumologicEnabled=no


#### EDIT ABOVE THIS LINE ####
dockerImageEnv="${_environment}"


dockerImage="900941654131.dkr.ecr.us-east-1.amazonaws.com/digital-fitnesse:${artifactVersion}-${buildNumber}-${dockerImageEnv}"
gitwatcherDockerImage="900941654131.dkr.ecr.us-east-1.amazonaws.com/digital-gitwatcher:${artifactVersion}-${buildNumber}-${dockerImageEnv}"
templateURL="https://s3.amazonaws.com/IDEXX-Digtal-Autodeploy/fitnesse/${_environment}/${artifactVersion}.${buildNumber}/fitnesse_ecs.template"
stackName="digital-fitnesse-ecs-${_environment}"

## Some functions we use
wait_for_stack_complete() {
	#stackID=$1

	echo "Waiting on stack update/create to complete"
	local stackStatus=$(aws cloudformation --region us-east-1 describe-stacks --stack-name $1 --query 'Stacks[].StackStatus' --output text)

	## Wait for stack to fully create
	while [[ "${stackStatus}" != "CREATE_COMPLETE" ]] && [[ "${stackStatus}" != "UPDATE_COMPLETE" ]] && [[ "${stackStatus}" != "UPDATE_ROLLBACK_COMPLETE" ]]
	do
		if [[ "${stackStatus}" = "CREATE_FAILED" ]] || [[ "${stackStatus}" = "ROLLBACK"* ]] || [[ "${stackStatus}" = "UPDATE_FAILED" ]]; then
			echo "Stack Creation Failed - ${stackStatus}"
			exit 1
		fi
		sleep 10
		stackStatus=$(aws cloudformation --region us-east-1 describe-stacks --stack-name $1 --query 'Stacks[].StackStatus' --output text)
	done

	if [[ "${stackStatus}" = "UPDATE_ROLLBACK_COMPLETE" ]]; then
		echo "Stack Update failed and resulted in a rollback.  Previous version of the stack should now be runnning"
		exit 1
	fi
}

## After stack complete we still need to wait for the ELB status to go InService
## This can be done by querying the service events and looking for service $SERVICE has reached a steady state.
wait_for_service_ready() {
	#stackName=$1
    #startDate=$2

	echo "Waiting on service to become ready."
	local clusterName=$(aws cloudformation --region us-east-1 describe-stack-resources --stack-name $1 --query 'StackResources[?ResourceType==`AWS::ECS::Cluster`].PhysicalResourceId' --output text)
	local serviceName=$(aws cloudformation --region us-east-1 describe-stack-resources --stack-name $1 --query 'StackResources[?ResourceType==`AWS::ECS::Service`].PhysicalResourceId' --output text)

	local serviceLive=$(aws ecs --region us-east-1 describe-services --cluster $clusterName --service $serviceName --query "services[].events[?to_string(createdAt) > '$2' && to_string(contains(message, 'reached a steady state')) == 'true'].id" --output text)
	loopCounter=0
	while : ; do
		if [[ -z ${serviceLive} ]]; then
			echo "Service is not yet ready"
		else
			echo "Service is ready"
			break
		fi

		local loopCounter=$((loopCounter+1))
		if [[ ${loopCounter} -gt 30 ]]; then
			echo "Service ready timeout (longer than 10 minutes).  Exiting build.  Please check the status of the stack manually."
			#TODO - add post build step to deploy build again with parameters from last good build"
			exit 1
		fi
		sleep 20
		serviceLive=$(aws ecs --region us-east-1 describe-services --cluster $clusterName --service $serviceName --query "services[].events[?to_string(createdAt) > '$2' && to_string(contains(message, 'reached a steady state')) == 'true'].id" --output text)
	done

	echo "Service is now ready"
}

update_ecs_service_count() {
	#clusterName=$1
	#serviceName=$2
	#desiredCount=$3

	echo "Update service count to $3."
	aws ecs --region us-east-1 update-service --cluster $1 --service $2 --desired-count $3
}

do_stack_operation() {
	#operation=$1

	local stackID=$(aws cloudformation --region us-east-1 $1 --capabilities CAPABILITY_IAM --stack-name ${stackName} --template-url ${templateURL} \
     --parameters ParameterKey=artifactVersion,ParameterValue=${artifactVersion} \
     ParameterKey=containerPort,ParameterValue=${containerPort} \
     ParameterKey=cpu,ParameterValue=${cpu} \
     ParameterKey=asgDesiredCapacity,ParameterValue=${asgDesiredCapacity} \
     ParameterKey=datadogEnabled,ParameterValue=${datadogEnabled} \
     ParameterKey=dockerImage,ParameterValue=${dockerImage} \
     ParameterKey=environment,ParameterValue=${environment} \
     ParameterKey=hostPort,ParameterValue=${hostPort} \
     ParameterKey=imageId,ParameterValue=${imageId} \
     ParameterKey=instanceType,ParameterValue=${instanceType} \
     ParameterKey=keyName,ParameterValue=${keyName} \
     ParameterKey=asgMaxSize,ParameterValue=${asgMaxSize} \
     ParameterKey=asgMinSize,ParameterValue=${asgMinSize} \
     ParameterKey=gitwatcherMemory,ParameterValue=${gitwatcherMemory} \
     ParameterKey=memory,ParameterValue=${memory} \
     ParameterKey=gitwatcherDockerImage,ParameterValue=${gitwatcherDockerImage} \
     ParameterKey=serviceCapacity,ParameterValue=${serviceCapacity} \
     ParameterKey=sumologicEnabled,ParameterValue=${sumologicEnabled} --output text)

     echo "$stackID"

}
## Assess the state of the existing CloudFormation stack and ASG
#
stackID=$(aws cloudformation --region us-east-1 describe-stacks --stack-name ${stackName} --query 'Stacks[].StackId' --output text)

if [[ -n "${stackID}" ]] ; then
  stackStatus=$(aws cloudformation --region us-east-1 describe-stacks --stack-name ${stackName} --query 'Stacks[].StackStatus' --output text)
fi


## 3 Scenarios to deal with:
#
# 1.  Stack is up and healthy, we need to issue a stack update
# 2.  Stack is not build, we need to launch a new one.
# 3.  Stack is in progress creating - we need to abort to not get in a weird spot
# 4.  Stack is in rollback, delete it and launch a new one
#
# Any paths outside of this flow should be an error requiring manual intervention


if [[ "${stackStatus}" = "CREATE_COMPLETE" ]] ; then
    echo "Stack Online.  Updating Stack"
    updateStack=1
elif [[ "${stackStatus}" = "UPDATE_COMPLETE" ]] || [[ "${stackStatus}" = "UPDATE_ROLLBACK_COMPLETE" ]] ; then
    echo "Stack Online.  Updating Stack"
    updateStack=1
elif [[ "${stackStatus}" = "CREATE_IN_PROGRESS" ]] ; then
    echo "Stack is already launching.  Aborting build"
    exit 1
elif [[ "${stackStatus}" = "UPDATE_IN_PROGRESS" ]] ; then
    echo "Stack is already updating.  Aborting build."
    exit 1
elif [[ "${stackStatus}" = "DELETE_IN_PROGRESS" ]] ; then
    echo "Stack is either already deleting, or an update is completing.  Aborting build."
    exit 1
elif [[ "${stackStatus}" = "UPDATE_ROLLBACK_FAILED" ]] ; then
    echo "Stack rollback failed.  Manually inspect and correct error causing failure, and then manually instruct Cloudformation to continue rolling back."
    echo "Note most errors can be resolved by simply terminating the offending EC2 instance and letting the ASG rebuild."
    exit 1
elif [[ "${stackStatus}" = "ROLLBACK"* ]] ; then
    echo "Stack is rolling back.  Wait a few minutes and try again."
    exit 1
elif [[ -z "${stackStatus}" ]] ; then
    echo "No Stack Online.  Starting Stack"
    startStack=1
else
    echo "Something went wrong identifying stacks.  Please review status of blue and green stacks before running again."
    exit 1
fi


## Create the new CloudFormation stack
#

if [ "${DESIRED_SERVICE_CAPACITY}" -gt "${DESIRED_CLUSTER_CAPACITY}" ] ; then
	echo "Cannot launch stack with service count > cluster size."
	exit 1
fi

startDate=$(date +%s)

if [[ "${startStack}" = "1" ]] ; then

    stackID=$(do_stack_operation "create-stack")

    #Need time for stack status to update - else immediate call will likely return previous state
	sleep 30
	wait_for_stack_complete $stackID
	wait_for_service_ready $stackName $startDate
elif [[ "${updateStack}" = "1" ]] ; then
    ## Here we need to decrement the in service capacity by 1 in order to give ourselves headroom to deploy the new service
    stackID=$(do_stack_operation "update-stack")


	if [ -n "${stackID}" ]; then
		#Need time for stack status to update - else immediate call will likely return previous state
		sleep 30
		wait_for_stack_complete $stackID
		wait_for_service_ready $stackName $startDate
	fi

	if [[ -z ${stackID} ]]; then
	echo "Stack has no updates".
	exit 0
	fi
fi

# Set the ECS deployment options manually until Cloudformation supports this.
clusterName=$(aws cloudformation --region us-east-1 describe-stack-resources --stack-name ${stackName} --query 'StackResources[?ResourceType==`AWS::ECS::Cluster`].PhysicalResourceId' --output text)
serviceName=$(aws cloudformation --region us-east-1 describe-stack-resources --stack-name ${stackName} --query 'StackResources[?ResourceType==`AWS::ECS::Service`].PhysicalResourceId' --output text)
if [[ "${DESIRED_SERVICE_CAPACITY}" = "1" ]] ; then
	minimumHealthyPercent=0
else
	minimumHealthyPercent=50
fi
aws ecs --region us-east-1 update-service --cluster ${clusterName} --service ${serviceName} --deployment-configuration maximumPercent=200,minimumHealthyPercent=${minimumHealthyPercent}


######

exit 0

