# Juggle

**Juggle** is a low-code and AI-powered API orchestration and system integration platform.

It enables you to quickly combine simple APIs and services into complex workflows and expose them as reusable interfaces. The resulting APIs can be consumed directly by frontend applications, other services, or external systems.

Juggle helps teams improve development efficiency, reduce implementation costs, and avoid unnecessary custom code.

## What is Juggle?

Modern applications often need to integrate multiple microservices, third-party systems, databases, and business services.

With Juggle, you can visually orchestrate these capabilities into a single workflow:

```text
Client Request
      │
      ▼
┌──────────────┐
│  Juggle Flow │
└──────────────┘
      │
      ├── Call Microservice A
      ├── Call Microservice B
      ├── Query Database
      ├── Transform Data
      ├── Execute Custom Code
      │
      ▼
Unified Response
```

Instead of writing a large amount of integration and orchestration code, developers and integration teams can design and manage workflows through Juggle.

---

## When Should You Use Juggle?

Juggle is useful in several scenarios.

### 1. Compose Existing Services into New Products

If your organization already has multiple backend services, Juggle can orchestrate them to quickly create new APIs and products.

### 2. Integrate with Third-Party Systems

Connect to external systems and services through visual workflows instead of building dedicated integration services for every use case.

### 3. Build a Backend-for-Frontend (BFF) Layer

Juggle can be used as a BFF layer that aggregates data from multiple backend services and provides APIs optimized for frontend applications.

This can reduce the need to build custom BFF services using technologies such as Node.js.

### 4. Support Enterprise Customization

For products that require frequent customer-specific customization or private deployments, Juggle allows teams to implement customized workflows without modifying or polluting the core product codebase.

---

# Features

## Workflow Version Management

Juggle supports multiple versions of workflows and provides built-in capabilities for gradual rollout and version-based execution.

## Flexible Data Types

Supports commonly used data structures, including:

* Strings
* Booleans
* Integers
* Decimal numbers
* Dates
* Time
* Lists
* Objects

## Visual Workflow Nodes

Juggle provides multiple node types for designing workflows, including:

* Method nodes
* Conditional nodes
* Code nodes
* Assignment nodes
* Database nodes
* Parallel execution nodes
* Cache nodes

These nodes can be combined to implement complex business workflows.

## Multiple Programming Languages

Extend workflows using custom code written in:

* Groovy
* JavaScript
* Python
* Java

## Multiple Communication Protocols

Juggle supports integration and orchestration across different protocols, including:

* HTTP
* Dubbo
* Web Services

## Integration Suite Marketplace

The platform provides reusable integration suites for commonly used systems and services, helping teams reduce the complexity of building integrations.

Examples include integrations for:

* AI services
* DingTalk robots
* Email services
* Cloud messaging services

## Multiple Database Support

Juggle supports multiple database technologies, including:

* MySQL
* Dameng
* TiDB
* OceanBase
* Doris

and other supported data sources.

---

# Workflow Types

Juggle supports different types of workflows for different business scenarios.

## 1. Synchronous Workflows

A synchronous workflow immediately returns the execution result to the caller.

### Use Cases

Suitable for scenarios requiring real-time responses, such as:

* Microservice API orchestration
* Backend-for-Frontend APIs
* Real-time system integration

---

## 2. Asynchronous Workflows

An asynchronous workflow executes independently, and the caller retrieves the result through a separate mechanism.

### Use Cases

Suitable for workflows that are time-consuming or do not require an immediate response, such as:

* Data processing
* Data cleaning
* Long-running business operations

---

## 3. Scheduled Workflows

Scheduled workflows execute at a specific time or on a recurring schedule.

### Use Cases

Suitable for:

* Scheduled data synchronization
* Periodic business processes
* Batch processing

---

## 4. Listener Workflows

Listener workflows execute when messages or events are received from external systems or message queues.

### Use Cases

Suitable for:

* Event-driven processing
* Message-based integration
* Data processing pipelines
* Data labeling and processing workflows

---

# Quick Start

## Prerequisites

Juggle requires Java to run.

### Recommended Environment

* 64-bit operating system
* Linux, Unix, macOS, or Windows
* JDK 21

Linux, Unix, or macOS environments are recommended.

---

## Download

Download the latest stable version of Juggle and extract the package.

For Linux, Unix, or macOS:

```bash
tar -xvf juggle-server-$version.tar.gz
```

---

## Start Juggle

The startup scripts are located in the `juggle/bin` directory.

### Windows

Run:

```text
startup.cmd
```

### Linux / Unix / macOS

Run:

```bash
sh startup.sh
```

---

## Access the Console

After Juggle starts successfully, open:

```text
http://127.0.0.1:9127
```

Default credentials:

```text
Username: juggle
Password: juggle
```

> For production environments, make sure to change the default credentials.

---

# Architecture and Use Cases

Juggle can be used as an orchestration layer between applications and backend services.

```text
                 ┌──────────────┐
                 │  Frontend /  │
                 │ AI / Client  │
                 └──────┬───────┘
                        │
                        ▼
                 ┌──────────────┐
                 │    Juggle    │
                 │ Orchestration│
                 └──────┬───────┘
                        │
        ┌───────────────┼────────────────┐
        ▼               ▼                ▼
   Microservices    Databases      Third-Party APIs
```

Typical use cases include:

* API orchestration
* Microservice composition
* System integration
* Backend-for-Frontend (BFF)
* Enterprise customization
* Data synchronization
* Event-driven workflows
* Scheduled automation

---

# Example

A single API can orchestrate multiple backend services.

```text
Client
   │
   ▼
Customer API
   │
   ├── Customer Service
   │
   ├── Order Service
   │
   ├── Product Service
   │
   └── Database
   │
   ▼
Unified Customer Response
```

This allows frontend applications to call a single API instead of coordinating multiple backend services.

---

# Documentation

Official documentation:

https://juggle.plus

Demo environment:

https://demo.juggle.plus/#/login

The demo environment supports login through supported third-party authentication providers.

---

# License

Juggle is licensed under the **GNU General Public License v3.0 (GPL-3.0)**.

See the `LICENSE` file for more information.

---

# Contributing

Contributions, bug reports, feature suggestions, and feedback are welcome.

If you find Juggle useful, please consider giving the repository a ⭐ on GitHub.

Your support helps the project continue to improve.
