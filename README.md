# DevOps Engineer Assignment - Spring Boot, Kubernetes, CI/CD

This repository contains a fully automated, Kubernetes-ready deployment for a Spring Boot application integrated with MySQL and Apache Kafka. 

## 🏗️ Architecture & Stack
* **Application:** Java 17, Spring Boot 3
* **Containerization:** Multi-stage Docker build utilizing `eclipse-temurin:17-jre-jammy` for an optimized footprint.
* **Orchestration:** Kubernetes (Minikube compatible) with isolated namespaces (`dev`, `qa`).
* **Database & Messaging:** MySQL 8.0 and Apache Kafka (KRaft mode) via Docker Compose.
* **CI/CD:** GitHub Actions (Build, Test, Push to Docker Hub).

## 🚀 Setup & Run Instructions

### 1. Prerequisites
Ensure you have the following installed:
* Docker & Docker Compose V2
* Minikube & Kubectl
* Java 17 & Maven (Optional, for local building outside docker)

### 2. Start the Infrastructure (MySQL & Kafka)
We use a Docker-composed infrastructure to run the DB and Message Broker without hardcoding secrets into the application layer.
```bash
docker compose up -d
