**[English](README.md)**

<div align="center">

<img src="src/assets/pocketpal-dark-v2.png" alt="PocketPal AI logo" width="120" />

# PocketPal AI (局域网增强定制版)

**一个完全在手机上运行的私有 AI 助手，专为局域网共享优化。**

与语言模型聊天，赋予它们声音，并让它们使用工具——所有这一切都在设备本地完成。无需账号，无云端依赖，甚至无需联网即可在你的手机上运行大语言模型。它同时作为一个兼容 OpenAI 接口的局域网 API，供其他设备（如电脑、平板）白嫖手机算力。

**本仓库为个人定制分支，解锁了原版隐藏的局域网 API 服务器，并加入了后台保活与智能数据清洗功能。**

<a href="https://pocketpal.dev/"><strong>官方网站</strong></a> ·
<a href="https://pocketpal.dev/leaderboard">排行榜</a> ·
<a href="https://palshub.ai/">PalsHub</a> ·
<a href="https://github.com/a-ghorbani/pocketpal-ai/discussions">讨论区</a>

<br/>

[![Build Status](https://img.shields.io/github/actions/workflow/status/PC267/pocketpal-ai/CPU&GPU-Universal-apk.yml?branch=main&label=Build&logo=github)](https://github.com/PC267/pocketpal-ai/actions)
[![Latest release](https://img.shields.io/github/v/release/PC267/pocketpal-ai?sort=semver)](https://github.com/PC267/pocketpal-ai/releases)
[![License: MIT](https://img.shields.io/github/license/PC267/pocketpal-ai)](LICENSE)
[![Stars](https://img.shields.io/github/stars/PC267/pocketpal-ai)](https://github.com/PC267/pocketpal-ai/stargazers)

</div>

---

> ⚠️ **声明**：本项目为个人学习与折腾测试用的魔改版本，**未上传至任何应用商城（App Store / Google Play）**。请勿在应用商店搜索本项目。如需安装，请通过 GitHub Actions 自行编译或下载 Release 中的构建产物。

## ✨ 独家定制特性（与原版的区别）

为了极致的局域网共享体验，我们进行了以下深度的二次开发：

* 🔓 **彻底解锁 Local API Server**：移除了原版必须购买 Plus 才能开启 API 服务器的付费墙，完全免费开放。
* 🔌 **默认端口定制**：将 API 默认监听端口修改为 **`8083`**，避免与常用端口冲突。
* 🧹 **智能 API 兼容清洗**：自研了请求清洗逻辑，**强行剥离第三方客户端发来的 `tools` 参数**，并修复了格式异常的 `messages` 角色。完美兼容 `DeepSeek Harness`、`Chatbox`、`NextChat` 等所有主流客户端，拒绝 400/500 报错。
* ✂️ **定向上下文截断**：针对手机内存有限的痛点，加入了智能清洗逻辑，自动精简冗长的系统提示词，防止大模型被复杂 Agent 的废话瞬间撑爆（Context is full）。
* 🔋 **前台服务保活（Foreground Service）**：接入了 Android 前台服务机制。开启 API 服务后，通知栏会显示常驻通知，**息屏或切到后台时，API 接口不再断连**，真正成为一台 24 小时在线的微型服务器。
* ⚡ **极速 CI/CD 打包**：内置了 GitHub Actions 工作流（跳过从源码编译 C++ 的漫长过程），约 10 分钟即可构建出体积小巧的可用 APK。

---

## 📥 如何获取此定制版 APK？

由于本版本未上架应用商店，你需要通过以下方式获取安装包：

**方式一：利用 GitHub Actions 自动编译（推荐）**
1. 点击本仓库顶部的 **Actions** 标签页。
2. 在左侧选择 `Build PocketPal (CPU/GPU Universal)`（适用于绝大多数手机，包括天玑、麒麟及部分骁龙芯片）。
3. 点击右侧的 **Run workflow** 按钮。
4. 等待约 10 分钟，在构建完成后的页面底部 **Artifacts** 区域下载 `PocketPal-Universal-APK.zip`。
5. 解压后安装 `app-prod-debug.apk`。

**方式二：本地编译**
如果你熟悉 React Native 开发，克隆本仓库后执行 `yarn install` 和 `yarn android` 即可直接编译运行。

---

## 📱 如何在局域网使用 API？

<div align="center">
  <img src="assets/images and logos/stack-diagram-dark.png" alt="手机端 AI 运行架构" width="100%">
  <p><i>手机端 AI 运行架构：UI与工具 → 桥接层 → 引擎层 → 硬件层</i></p>
</div>

1. 在手机 App 中下载并加载任意 GGUF 模型。
2. 进入 App 设置页面，滑到底部找到 **Local API Server**。
3. 开启 `Serve the loaded model` 开关，并开启 `Allow other devices on the network` (LAN) 开关。
4. 界面会显示类似 `http://192.168.x.x:8083/v1` 的地址。
5. 在你的电脑端（如 Chatbox 软件）中配置：
   * **API 地址**：填入上面的 URL。
   * **API Key**：随便填（例如 `sk-1234`）。
   * **模型名称**：随便选或填入手机上的模型名（服务端已强行覆盖，填什么都可以）。
6. 开始畅聊！你甚至可以在电脑端用代码调用这个接口。

---

## 🧠 原版核心特性与截图

<details>
<summary><strong>📥 下载与加载模型</strong></summary>

<br/>

1. 打开 App，点击 **菜单** (☰)，然后进入 **模型 (Models)**。
2. 从列表中选择一个模型并点击 **下载**，或者点击 **+** 从 Hugging Face 或本地存储中添加。
3. 在 Hugging Face 中，搜索 GGUF 模型并选择适合你设备内存和存储的量化版本——可以立即下载或加入书签稍后下载。
4. 下载完成后，点击 **加载 (Load)**（或者使用聊天输入框左侧的箭头图标直接从聊天界面加载）。

<img src="assets/images and logos/Download_models.png" alt="下载模型" width="100%">
</details>

<details>
<summary><strong>💬 聊天体验</strong></summary>

<br/>

1. 确保已加载模型。
2. 打开 **聊天 (Chat)** 页面开始对话。
3. 推理时屏幕会保持常亮，空闲时自动关闭。
4. 使用复制图标**复制**完整回复，或长按某一段落仅复制该部分。
5. 长按你的任意一条消息进行**编辑**——AI 会从你修改的地方重新生成。点击 **重试 (retry)** 获取全新回答，可选择使用不同模型。

<img src="assets/images and logos/Chat.png" alt="聊天界面" width="83%">
</details>

<details>
<summary><strong>🎭 Pals 角色与 PalsHub</strong></summary>

<br/>

创建个性化助手：
- **Assistant Pal (助手型)** —— 选择一个默认模型，设置系统提示词（自己编写或让 App 生成），并自定义聊天输入框颜色。
- **Roleplay Pal (角色扮演型)** —— 包含上述所有功能，外加地点、AI 角色和其他上下文参数。

在聊天页面使用 Pal 选择器切换人格。在 App 内浏览 **[PalsHub](https://palshub.ai/)** 发现社区 Pals，包括通过内购获取的高级 Pals。

<img src="assets/images and logos/Pals.png" alt="助手型 Pal" width="100%">
<p><em>创建一个鸡尾酒配方助手</em></p>
</details>

<details>
<summary><strong>📊 设备性能测试</strong></summary>

<br/>

1. 打开 **性能测试 (Benchmark)** 页面。
2. 运行性能测试，比较不同模型的速度和效率。
3. 查看每秒生成的 Token 数 (tokens/sec) 和内存使用情况。
4. 可选择将你的结果分享到 [AI Phone Leaderboard](https://pocketpal.dev/leaderboard)。

<img src="assets/images and logos/Benchmark.png" alt="性能测试" width="100%">
</details>

<details>
<summary><strong>🔑 设置 Hugging Face 令牌（用于受限模型）</strong></summary>

<br/>

1. 在你的 Hugging Face 账户中创建一个访问令牌（[官方文档](https://huggingface.co/docs/hub/en/security-tokens)）。
2. 在 PocketPal 中，进入 **设置 → 设置令牌 (Set Token)**，粘贴并保存。

<img src="assets/images and logos/Token_in_pocketpal.png" alt="令牌设置" width="66%">
</details>

---

## 📄 开源协议

基于 [MIT License](LICENSE) 协议开源。本项目 Fork 自原作者 [a-ghorbani/pocketpal-ai](https://github.com/a-ghorbani/pocketpal-ai)。

<div align="center">
<br/>

Made with ❤️ for people who want AI that stays on their phone.

<br/>

<sub>如果这个定制版对你有帮助，欢迎给个 ⭐ 支持一下！</sub>

</div>
