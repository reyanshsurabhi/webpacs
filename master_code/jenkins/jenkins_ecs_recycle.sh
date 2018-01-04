#!/bin/bash -x

stackName=digital-fitnesse-ecs-${ENVIRONMENT}


## Function to wait for service ready state
wait_for_service_ready() {
	local startDate=$(date +%s)
	local serviceLive=$(aws ecs --region us-east-1 describe-services --cluster $clusterName --service $serviceName --query "services[].events[?to_string(createdAt) > '$startDate' && to_string(contains(message, 'reached a steady state')) == 'true'].id" --output text)
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
			echo "Service ready timeout (longer than 10 minutes).  Exiting build.  Please check the status of the service manually."
			exit 1
		fi
		sleep 20
		serviceLive=$(aws ecs --region us-east-1 describe-services --cluster $clusterName --service $serviceName --query "services[].events[?to_string(createdAt) > '$startDate' && to_string(contains(message, 'reached a steady state')) == 'true'].id" --output text)
	done

	echo "Service is now ready"
}


## Check stack status to make sure other operations aren't in progress and that the stack exists
stackStatus=$(aws cloudformation --region us-east-1 describe-stacks --stack-name $stackName --query 'Stacks[].StackStatus' --output text)
if [[ "${stackStatus}" = *"PROGRESS" ]] || [[ -z "${stackStatus}" ]]; then
	echo "Service Recycle Failed: ${stackStatus}"
	exit 1
fi

## Get physical ids
clusterName=$(aws cloudformation --region us-east-1 describe-stack-resources --stack-name $stackName --query 'StackResources[?ResourceType==`AWS::ECS::Cluster`].PhysicalResourceId' --output text)
serviceName=$(aws cloudformation --region us-east-1 describe-stack-resources --stack-name $stackName --query 'StackResources[?ResourceType==`AWS::ECS::Service`].PhysicalResourceId' --output text)

## Get current desired count
desiredCount=$(aws ecs --region us-east-1 describe-services --cluster $clusterName --service $serviceName --query 'services[].desiredCount' --output text)

## Shrink desired count
aws ecs --region us-east-1 update-service --cluster $clusterName --service $serviceName --desired-count 0

## Wait for ready state
wait_for_service_ready

echo "Service has desiredCount=0. Updating count."

## Update desired count
aws ecs --region us-east-1 update-service --cluster $clusterName --service $serviceName --desired-count $desiredCount

## Wait for ready state
wait_for_service_ready