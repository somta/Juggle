---
title: 如何贡献
description: 如何给Juggle贡献代码，Juggle有哪些贡献源码的方式？
keywords:
  - Juggle源码
  - PR源码
  - Juggle开源贡献
createTime: 2024/10/18 15:09:38
permalink: /docs/guide/open/contributing-flow/
---

#  如何贡献

我们非常欢迎您的贡献和加入，无论是微不足道的清理或大的新功能。我们希望为每个编程语言提供高质量、有良好文档的代码。

这也不是代码是唯一有贡献项目的方式。我们非常重视文档、与其他项目的集成，并欣然接受这些方面的改进。

## 社区贡献

- 点亮Star，照亮Juggle开源之路!!
    - [https://github.com/somta/Juggle](https://github.com/somta/Juggle)
- 完善[Juggle文档](https://github.com/somta/Juggle/docs)
- 在你自己的博客、微博、微信公众号、vlog 等自媒体分享有关Juggle的一切。也非常欢迎将实战内容通过PR进行贡献合并在Juggle的官方文档进行展示。
- 把Juggle分享给更多的人


## 源码贡献

### 1.fork somta/Juggle项目到您的github库

### 2.克隆您fork的Juggle代码仓库到您本地

```
git clone ${your fork Juggle repo address}

cd Juggle
```

### 3.添加somta/Juggle仓库为upstream仓库

```
git remote add upstream https://github.com/somta/Juggle.git

git remote -v 

    origin	   ${your fork nacos repo address} (fetch)
    origin	   ${your fork nacos repo address} (push)
    upstream	https://github.com/somta/Juggle.git (fetch)
    upstream	https://github.com/somta/Juggle.git (push)
    
git fetch origin
git fetch upstream
```

#### 4.选择一个开发的基础分支，通常是develop，并基于此创建一个新的分支

```
(在本地基于develop分支创建开发修复分支, 通常以该PR对应的issue号作为开发分支名）
git checkout -b develop-issue#${issue-number} develop
```

#### 5.在本地新建的开发分支上修复对应的问题

修改时请保证该分支上的修改**仅和issue相关**，并尽量细化，做到**一个分支只修改一件事，一个PR只修改一件事**

提交修改记录请尽量使用英文描述，遵守标准的提交规范，如果有具体的issue号，可以在提交信息标明，如： Fix: #10023 修复流程表达式执行异常

#### 6.Rebase基础分支和开发分支

您修改的时候，可能别人的修改已经提交并被合并，此时可能会有冲突，这里请使用rebase命令进行合并解决，主要有2个好处：

1. 您的提交记录将会非常优雅，不会出现`Merge xxxx branch` 等字样
2. rebase后您分支的提交日志也是一条单链，基本不会出现各种分支交错的情况，回查时更轻松

```
git fetch upstream

git rebase -i upstream/develop
```

#### 7.将您开发完成rebase后的分支，上传到您fork的仓库

```
git push origin develop-issue#${issue-number}
```

#### 8.按照拉取请求模板中的清单创建Pull Request

我们为您提供了[Pull Request模板](/docs/notes/guide/open/pull-request)，填写模板中的信息即可，模板的信息是为了让我们更快验证您提交的PR，以便合并到基础分支，让更多人收益，请您认真填写。

#### 9.如果代码没有问题，我们会把您的修改合并到基础分支中，恭喜您成为Juggle的官方贡献者