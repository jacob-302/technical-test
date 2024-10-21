helm-brave:
	curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash

install-concourse:
	helm repo add concourse https://concourse-charts.storage.googleapis.com/ && \
	helm repo update && \
	helm upgrade --install concourse concourse/concourse -n concourse \
		-f infra/concourse-values.yaml

install-sonarqube:
	helm repo add sonarqube https://SonarSource.github.io/helm-chart-sonarqube && \
	helm repo update && \
	helm upgrade --install -n sonarqube sonarqube sonarqube/sonarqube \
		-f infra/sonarqube-values.yaml

install-harbor:
	kubectl apply -f infra/harbor-secrets.yaml -n harbor && \
	helm repo add harbor https://helm.goharbor.io && \
	helm repo update && \
	helm upgrade --install -n harbor harbor harbor/harbor \
		-f infra/harbor-values.yaml


minikube-start:
	minikube start --cpus=8 --memory=6G --addons=ingress

NAMESPACES = sonarqube concourse harbor
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

docker-build:
	cd api && \
	docker build -t technical-test-app --build-arg=git_sha="$$(git rev-parse HEAD | tr -d "\n")" . && \
	cd ..


docker-run:
	docker run -d -p 8080:8080 --name technical-test-app technical-test-app

docker-stop:
	docker kill technical-test-app && \
	docker rm technical-test-app