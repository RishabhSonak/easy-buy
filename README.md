EASY-BUY
This repository contains a microservices-based eCommerce application built using Spring Boot and Spring Cloud. It provides a basic eCommerce platform with separate services for product management, user details, cart, authentication, and an API gateway for routing.

## Table of Contents

- [Architecture Overview](#architecture-overview)
- [Microservices](#microservices)
  - [Products Service](#products-service)
  - [User Details Service](#user-details-service)
  - [Cart Service](#cart-service)
  - [Auth Service](#auth-service)
  - [Gateway Service](#gateway-service)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)





## Architecture Overview

This eCommerce application is structured as a set of microservices, each dedicated to a specific domain area, which communicate with each other via HTTP and are routed through an API Gateway.
 The architecture consists of independent, decoupled services making it possible to scale, deploy, and manage services separately. Each service has its own database and business logic, promoting modularity, scalability, and fault tolerance.
Service discovery and Centralized configuration management is used to enable dynamic location of services, allowing them to register and locate one another within the environment and easy management of configurations.
