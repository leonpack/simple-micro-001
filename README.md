# Microservices Project

Welcome to the Microservices Project! This repository demonstrates a microservices architecture using various Spring Cloud components and monitoring tools.

## Features

- **Service Discovery**: Managed by **Eureka** for dynamic service registration and discovery.
- **API Gateway**: Using **Spring Cloud Gateway** for intelligent routing and request handling.
- **Centralized Configuration**: Managed by **Spring Cloud Config Server**.
- **Monitoring and Administration**: With **Spring Boot Admin** for monitoring and **Micrometer** for application metrics.
- **Distributed Tracing**: Enabled via **Zipkin** for tracing requests across services.
- **Actuator Metrics**: Includes health checks, metrics, and more.
- **Scalable Design**: Built to handle dynamic scaling and resilience.

---

## Architecture Overview

```plaintext
┌────────────────────────┐
│   Spring Cloud Config  │
│   Centralized Config   │
└───────────┬────────────┘
            │
┌───────────▼────────────┐    ┌──────────────┐
│        Eureka          │<-->|    Admin     │
│ Service Registry       │    │   Monitor    │
└───────────┬────────────┘    └──────────────┘
            │
 ┌──────────▼───────────┐
 │ Spring Cloud Gateway │
 │  API Gateway         │
 └──────────┬───────────┘
            │
 ┌──────────▼───────────┐
 │      Microservices    │
 │ (Business Logic Layer)│
 └──────────┬───────────┘
            │
 ┌──────────▼───────────┐
 │       Database        │
 └───────────────────────┘
