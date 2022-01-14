# 主要使用的开源组件

# Legion 使用的开源组件

Legion 完全基于开源产品打造。以下我们从两个方面来阐述 Legion 使用了哪些开源产品和工具：

## 应用框架使用的开源工具集

Legion 的应用系统架构由四个不同的层组成，从应用程序代码到所需运行平台和连接服务。这些应用程序和服务通过一致的调度和监督进行管理。

![img](https://c0c0.oss-cn-qingdao.aliyuncs.com/d94d.png)

### 应用前端

Legion 前端使用 Vue 和 Vuex。

核心组件有：

- **Vue**：Vue 是一个用于构建用户界面的渐进式 JavaScript 框架。
- **Vuex**：Vuex 是一个专为 Vue.js 应用程序开发的状态管理模式。

### 后端

Legion 的后端使用 Spring Boot 作为开发核心脚手架。

核心组件有：

- **Spring Boot**：Spring Boot 是由 Pivotal 团队提供的全新框架，其设计目的是用来简化新 Spring 应用的初始搭建以及开发过程。该框架使用了特定的方式来进行配置，从而使开发人员不再需要定义样板化的配置。

### 数据服务层

Legion 采用 MySQL 作为关系型数据存储库，Redis 作为缓存库。

核心组件有：

- **MySQL**：Mysql 是最流行的开源关系型数据库管理系统。
- **Redis**：Redis 是一个开源的使用 ANSI C 语言编写、支持网络、可基于内存亦可持久化的日志型、Key-Value 数据库，并提供多种语言的 API。

### 运行环境

Legion 运行在 Docker 上，当然也可直接运行于Linux或Windows平台。

核心组件有：

- **Docker**：Docker 是一个开源的应用容器引擎，让开发者可以打包他们的应用以及依赖包到一个可移植的容器中，然后发布到任何流行的 Linux 机器上，也可以实现虚拟化。

### 编码

Legion 采用主流的 Git 和 Github、Gitlab 作为代码的管理和托管工具，同时使用 Maven 作为项目代码的组织和管理工具。

核心组件有：

- **Git**：Git 是一个开源的分布式版本控制系统，可以有效、高速的处理从很小到非常大的项目版本管理。
- **Gitlab**：GitLab 是一个基于 Git 的仓库管理程序，也是一个方便软件开发的强大完整应用。
- **Maven**：Maven 项目对象模型(POM)，可以通过一小段描述信息来管理项目的构建，报告和文档的软件项目管理工具

### 构建

Legion 在构建阶段，采用 Gitlab CI 作为持续集成工具，Harbor 作为镜像的存放库，同时 Legion 融合了 Gitlab CI 和 Harbor 这两个工具，以实现自动化和版本的控制。

- **Gitlab CI**：Gitlab CI 是 Gitlab 提供的一个持续集成工具。主要通过.gitlab-ci.yml 配置文件管理 CI 过程。
- **Harbor**：Harbor 是一个企业级的 Docker Registry，可以实现 images 的私有存储和日志统计权限控制等功能，并支持创建多项目。

### 测试

Legion 采用多个代码检查和测试工具，其中，TestNG 作为后端 Java 代码的测试工具；Selenium 作为前端测试的工具。

核心组件有：

- **TestNG**：TestNG 是一个测试 Java 应用程序的新框架，相比于JUnit更加强大、创新、可扩展、灵活。
- **Selenium**：Selenium 是一套完整的 web 应用程序测试系统，包含了测试的录制（selenium IDE），编写及运行（Selenium Remote Control）和测试的并行处理（Selenium Grid）。

### 部署和运营

Legion 融合使用 Docker、Kubernetes 和 Harbor 作为部署工具。

核心组件有：

- **Docker**：Docker 是一个开源的引擎，可以轻松的为任何应用创建一个轻量级的、可移植的、自给自足的容器。
- **Kubernetes**：Kubernetes 是一个开源平台，用于跨主机群集自动部署，扩展和操作应用程序容器，提供以容器为中心的基础架构。
- **Harbor**：Harbor 是一个企业级的 Docker Registry，可以实现 images 的私有存储和日志统计权限控制等功能，并支持创建多项目。

### 监控

Legion 的监控包括了全生命周期的状态、反馈、监控等，帮助开发和运营管理更好的提升效能。

核心组件有：

- **Spring Boot Actuator**：Actuator是Spring Boot提供的对应用系统的自省和监控的集成功能，可以对应用系统进行配置查看、相关功能统计等。
- **Spring Boot Admin**：Spring Boot Admin 用于监控基于 Spring Boot 的应用，它是在 Spring Boot Actuator 的基础上提供简洁的可视化 WEB UI。
- **Apache Druid**：Apache Druid是一个高性能的实时分析数据库，可以很好的监控DB池连接和SQL的执行情况，可以说是针对监控而生的DB连接池



### Legion 角色控制访问权限(RBAC)
#### Legion 对资源的管理是基于角色控制的，并从部门组织层、菜单权限层和用户层对角色进行划分。

包含如下的特点：

基于角色的权限访问控制
基于部门组织层、菜单权限层和用户层的三层权限体系
自定义角色创建和绑定
架构
![img](https://c0c0.oss-cn-qingdao.aliyuncs.com/sdskh.png)

###  资源
 遵循REST原则，视HTTP-based REST API为一个或一组资源。
 对资源的引用和操作则视为权限。
 角色是资源的超集。

角色和资源之间通过 RBAC 关联起来。
 一个角色严格遵循只能访问所属资源的原则。
 角色支持自定义角色。
 用户是资源的实际使用者。

用户和角色之间通过 RBAC 关联起来。
