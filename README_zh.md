**[English](README.md) | [简体中文](README_zh.md)**

# PocketPal AI (局域网增强定制版)

一个完全在手机上运行的私有 AI 助手。**本仓库为个人定制分支，解锁了原版隐藏的局域网 API 服务器，并加入了后台保活与智能数据清洗功能。**

无需账号，无云端依赖，甚至无需联网即可在你的手机上运行大语言模型，并作为局域网的 OpenAI API 接口供其他设备（如电脑、平板）白嫖算力。

> ⚠️ **声明**：本项目为个人学习与折腾测试用的魔改版本，**未上传至任何应用商城（App Store / Google Play）**。请勿在应用商店搜索本项目。如需安装，请通过 GitHub Actions 自行编译或下载 Release 中的构建产物。

---

## ✨ 独家定制特性（与原版的区别）

在这个分支中，我们为了极致的局域网共享体验，进行了以下深度的二次开发：

* 🔓 **彻底解锁 Local API Server**：移除了原版必须购买 Plus 才能开启 API 服务器的限制，完全免费开放。
* 🔌 **默认端口定制**：将 API 默认监听端口修改为 **`8083`**，避免与常用端口冲突。
* 🧹 **智能 API 兼容清洗**：自研了请求清洗逻辑，**强行剥离第三方客户端发来的 `tools` 参数**，并修复了格式异常的 `messages` 角色。完美兼容 `DeepSeek Harness`、`Chatbox`、`NextChat` 等所有主流客户端，拒绝 400/500 报错。
* ✂️ **定向上下文截断**：针对手机内存有限的痛点，加入了智能清洗逻辑，自动精简冗长的系统提示词，防止大模型被复杂 Agent 的废话瞬间撑爆（Context is full）。
* 🔋 **前台服务保活（Foreground Service）**：接入了 Android 前台服务机制。开启 API 服务后，通知栏会显示常驻通知，**息屏或切到后台时，API 接口不再断连**，真正成为一台 24 小时在线的微型服务器。
* ⚡ **极速 CI/CD 打包**：内置了 GitHub Actions 工作流（不强制编译底层 C++ 源码），约 10 分钟即可构建出体积小巧（约 135MB 压缩包）的可用 APK。

---

## 📥 如何获取此定制版 APK？

由于本版本未上架应用商店，你需要通过以下方式获取安装包：

**方式一：白嫖 GitHub Actions 自动编译（推荐）**
1. 点击本仓库顶部的 **Actions** 标签页。
2. 在左侧选择 `Build PocketPal (CPU/GPU Universal)`（适用于绝大多数手机，包括天玑、麒麟及部分骁龙）。
3. 点击右侧的 **Run workflow** 按钮。
4. 等待约 10 分钟，在构建完成后的页面底部 **Artifacts** 区域下载 `PocketPal-Universal-APK.zip`。
5. 解压后安装 `app-prod-debug.apk` 即可。

**方式二：本地编译**
如果你熟悉 React Native 开发，克隆本仓库后执行 `yarn install` 和 `yarn android` 即可直接编译运行。

---

## 📱 如何在局域网使用 API？

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

## 🧠 原版核心特性

* **端侧聊天** —— 完全离线运行 GGUF 格式的语言模型（Gemma, Qwen, Phi, Llama 等）。
* **文字转语音与语音输入** —— 支持设备本地的神经网络语音与 Whisper 听写。
* **Pals (角色)** —— 创建带有个性化模型和系统提示词的专属助手。
* **Hugging Face 集成** —— 直接从 HF Hub 搜索并下载模型。
* **硬件加速** —— 支持 CPU、GPU（Metal / OpenCL）和 NPU（高通 Hexagon，需自行开启编译源码）推理。

---

## 📄 开源协议

基于 [MIT License](LICENSE) 协议开源。本项目 Fork 自原作者 [a-ghorbani/pocketpal-ai](https://github.com/a-ghorbani/pocketpal-ai)。
