# Serendipity

## 简介

**Serendipity** （意为发现美好）是一个简单的微型博客项目，由 `Spring Boot(Maven)` 构建，其中使用了 `Spring MVC` `Spring Security` `Spring Data Jpa` , 数据库使用了 `MySQL`。

项目功能包含：

* 用户注册
* 用户登陆
* 发布信息
* 删除信息

*注：该项目中，用户称为 **Serendipper** 信息称为 **Serendimsg** 。*

## 详细介绍

### 实体

#### 用户（Serendipper）

在包含了一些必要属性的前提下，使用 `@OneToMany` 关联到 信息（Serendimsg）。

#### 信息（Serendimsg）

在包含了一些必要属性的前提下，使用 `@CreatedBy` 关联到 用户（Serendipper），以及 `@CreatedDate` 确定发送时间。

### Controller

调用 Service 提供的接口实现功能。

由于时间紧迫，没有编写 RESTful 风格的 Controller。而是使用传统的 `Thymeleaf` 模板引擎进行渲染。

### Service

接口 SerendipperService 和 SerendimsgService 为 Controller 层服务。

SerendipperServiceImpl 和 SerendimsgServiceImpl 实现了接口。

### DAO

接口 SerendipperRepository 和 SerendimsgRepository 继承 JpaRepository 为 Service 层服务。

因此不用特意实现这两个接口，只需要根据 Spring Data 的规定编写接口即可。

### Security

Security 使用Spring Security 控制。具体设置位于类  conglin.serendipity.config.WebSecurityConfig 中。

## 下载

```
git clone git@github.com:CongLinDev/Serendipity.git
```

## Preview

### Index

![index.png](https://i.loli.net/2019/03/09/5c836737097bc.png)

### Login

![login.png](https://i.loli.net/2019/03/09/5c83676d3ce0f.png)

### HomePage

![home.png](https://i.loli.net/2019/03/09/5c8367a8d963e.png)