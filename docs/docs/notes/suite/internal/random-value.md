---
title: 随机值
description: 如何使用随机值套件，并详细介绍随机值套件的方法
keywords:
  - 随机值
  - 唯一值
  - 随机时间
permalink: /suite/internal/random.html
createTime: 2024/10/18 18:29:05
---

随机值套件是官方推出的一个套件，主要解决各种随机值生成的问题，比如生成随机整数、生成随机小数、生成随机字符串、生成随机时间、生成密码、生成唯一值等随机值。

## 生成随机整数

方法提供了两个入参，最大整数和最小整数，输入一个最大的整数和一个最小的整数，生成一个范围内的随机整数

## 生成随机小数

方法提供了三个入参，最大小数，最小小数，精度（默认为2位小数），生成一个范围内的指定精度的随机小数

## 生成随机唯一值（UUID）

方法不需要提供出入参，直接生成一个UUID值

## 生成随机密码

方法提供了一个入参，密码长度（默认为6位），生成一个指定长度的随机字符串

## 生成随机日期

方法提供了两个入参，开始日期和结束日期，生成一个范围内的随机日期