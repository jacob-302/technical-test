helm-brave:
	curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash

install-concourse:
	helm repo add concourse https://concourse-charts.storage.googleapis.com/ && \
	helm upgrade --install concourse concourse/concourse -n concourse \
		-f infra/concourse-values.yaml

install-sonarqube:
	helm repo add sonarqube https://SonarSource.github.io/helm-chart-sonarqube && \
	helm repo update && \
	helm upgrade --install -n sonarqube sonarqube sonarqube/sonarqube \
		-f infra/sonarqube-values.yaml

minikube-start:
	minikube start --addons=ingress

NAMESPACES = sonarqube concourse
configure-namespaces:
	$(foreach namespace, $(NAMESPACES), kubectl create namespace $(namespace);)

minikube-delete:
	minikube delete

fly-login:
	fly -t concourse \
		login \
		--concourse-url http://concourse.technical-test.com

set-pipeline:
	fly -t concourse \
		set-pipeline \
		--pipeline java-build \
		--config concourse/java-build.yaml