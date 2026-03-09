# Stability, Cost & Ops Strategy

## 1. Ensuring Stability Across Dev / QA / Prod
To ensure stability and prevent cross-environment contamination, I implemented strict architectural isolation:
* **Namespace Isolation:** Deployments are segmented using Kubernetes Namespaces (`dev`, `qa`), ensuring a crash in one environment does not consume resources or affect routing in another.
* **Immutable Artifacts & Dynamic Configuration:** The CI/CD pipeline builds a single Docker image. Environment-specific variables (MySQL host, Kafka broker) are dynamically injected via **ConfigMaps** and **Secrets** at runtime, adhering strictly to 12-Factor App principles.
* **Probes:** I configured TCP Socket Liveness and Readiness probes. This ensures Kubernetes only routes external traffic to the Spring Boot pod once the Tomcat server is fully initialized, and automatically restarts the pod if the application deadlocks.

## 2. Rolling Back a Failed Deployment
If a deployment introduces a critical bug, the fastest and safest recovery mechanism is utilizing Kubernetes' native rollout history:
```bash
kubectl rollout undo deployment/spring-boot-app -n spring-boot-app
