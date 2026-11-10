**[English](README.md) | [简体中文](README_zh.md)**

# PocketPal AI

一个完全在手机上运行的私有 AI 助手。

与语言模型聊天，赋予它们声音，并让它们使用工具——所有这一切都在设备本地完成。无需账号，无云端依赖，甚至无需联网。

[pocketpal.dev](https://pocketpal.dev) · [获取应用](#获取应用) · [排行榜](https://pocketpal.dev/leaderboard) · [PalsHub](https://palshub.ai) · [讨论区](https://github.com/a-ghorbani/pocketpal-ai/discussions)

[![App Store](https://img.shields.io/badge/App_Store-Download-blue)](https://apps.apple.com/us/app/pocketpal-ai/id6502579498) [![Google Play](https://img.shields.io/badge/Google_Play-Download-green)](https://play.google.com/store/apps/details?id=com.pocketpalai)

## 为什么选择 PocketPal AI？

大多数 AI 应用只是连接他人服务器的一个窗口——你输入的每一条消息都会被发送、记录并分析在你视线之外的某个地方。PocketPal 反转了这一模式：**AI 运行在你的手机上，你的对话留在你的手机里**，除非你主动选择使用在线功能。

* 🔒 **默认私密** —— 除非你开启在线功能（如网页搜索或远程服务器），否则所有的提示词、回答和文档都留在你的设备上。
* ✈️ **离线可用** —— 下载一次模型后即可随时使用，无需网络连接，无需账号。在飞机上，在野外，随时随地。
* 📱 **在自有硬件上运行** —— 真实的大语言模型、语音和工具，针对你手机的 CPU、GPU 和 NPU 进行了优化。
* 🆓 **免费且开源** —— 聊天、模型、语音和工具全部免费，无需账号。应用商店版本包含可选的“PocketPal Plus”一次性买断，用于解锁免提语音功能；**而从源码构建的版本则直接包含所有 Plus 功能**。基于 MIT 协议开源。

**隐私声明**：你的聊天记录和文档仅会通过你主动开启的功能离开设备（例如：网页搜索/阅读、连接远程服务器，或使用手机自带的在线系统语音）。应用的其他网络使用（模型和语音下载、PalsHub 及内购、启动时从 jsDelivr CDN 获取的设备兼容性规则、选择性加入的跑分排行榜，以及你提交的反馈或报告）绝不会附带你的聊天内容。

## 目录
* [功能特性](#功能特性)
* [获取应用](#获取应用)
* [工作原理](#工作原理)
* [使用指南](#使用指南)
* [面向开发者](#面向开发者)
* [贡献代码](#贡献代码)
* [路线图](#路线图)
* [社区与支持](#社区与支持)
* [开源协议](#开源协议)

## 功能特性

* 🧠 **端侧聊天** —— 完全离线运行 GGUF 格式的语言模型（支持 Gemma、Qwen、Phi、Llama 等）。
* 🗣️ **文字转语音 (TTS)** —— 使用设备本地的神经网络语音（如 Kokoro、Kitten、Supertonic，支持设备上的 Phonon）为你的助手赋予声音，或使用手机的系统语音。
* 🎙️ **语音输入** —— 使用设备本地的 Whisper 语音识别（whisper.cpp）进行听写。
* 🎭 **Pals (角色)** —— 创建个性化的助手，赋予它们专属的模型、系统提示词和性格（支持助手和角色扮演模式）。
* 🛍️ **PalsHub** —— 发现并安装社区创建的 Pals，包括通过内购获取的高级 Pals。
* ✨ **PocketPal Plus** —— 应用商店和 Google Play 版本中可选的一次性买断功能，增加免提语音对话体验（从源码构建包含所有 Plus 功能）。
* 🛠️ **技能与工具 (Talents & tools)** —— 让有能力的 Pals 在工具调用循环中调用内置工具（计算器、日期/时间、富 HTML 渲染、选择性加入的网页搜索和页面阅读）。
* 📥 **Hugging Face 集成** —— 使用你的访问令牌直接从 HF Hub 搜索并下载 GGUF 模型，包括受限（Gated）模型。
* 📊 **性能测试** —— 测量每秒生成的 Token 数 (tokens/sec) 和内存占用，并选择性地在 AI Phone Leaderboard 上进行比较。
* ⚡ **硬件加速** —— 支持 CPU、GPU（iOS 上的 Metal，Android 上的 OpenCL/Adreno）和 NPU（高通 Hexagon）推理路径，并支持优雅降级。
* 🌍 **多语言支持** —— 支持 16 种语言，适配手机和平板电脑，完美支持 iPad。

## 获取应用

| 平台 | 下载方式 |
| :--- | :--- |
| iOS / iPadOS | 在 App Store 下载 |
| Android | 在 Google Play 获取 |

**三步开启你的第一次聊天：**
1. 从应用商店安装 PocketPal。
2. **下载模型** —— 点击菜单 (☰) → 模型 (Models)，选择一个适合你手机的模型并下载（或者从 Hugging Face 添加）。
3. **加载并开始聊天** —— 就这么简单，你现在已经可以在完全离线状态下运行 AI 了。

## 工作原理

你不需要了解这些也能使用 PocketPal，但如果你好奇手机是如何离线运行真实 AI 的，这里是简短的说明。

PocketPal 是一个包含四层的架构，从底层的芯片一直延伸到顶部的聊天 UI。每一层都有明确的职责，依赖方向严格自上而下——JS 应用与原生桥接层通信，桥接层与推理引擎通信，引擎调用底层硬件。

| 层级 | 负责的内容 |
| :--- | :--- |
| **UI 与工具** | React Native 应用（UI 使用 React Native Paper，状态管理使用 MobX，聊天记录存在 WatermelonDB）。`AgentRunner` 驱动每一轮聊天——流式输出 tokens，在模型调用时分发技能（工具），并输入结果进行后续推理。Pals 是可配置的人设；PalsHub 是用于分享和购买它们的应用内市场。在语音模式下，你的语音被转录、作为普通聊天处理，然后回复被朗读出来。 |
| **桥接层 (Bridging)** | 连接 JS 和引擎的原生模块。`llama.rn` 通过 JSI 桥接 LLM 推理；`whisper.rn` 桥接语音识别；`@pocketpalai/react-native-speech` 和 `onnxruntime-react-native` 桥接文本转语音。 |
| **引擎层 (Engine)** | 推理引擎。`llama.cpp` 运行量化 GGUF 格式的语言模型；`whisper.cpp` 运行 GGML 格式的语音模型；ONNX Runtime 运行 Kokoro、Kitten 等语音模型。 |
| **硬件层 (Hardware)** | 数学运算实际发生的地方。PocketPal 支持 CPU（通用回退方案）、GPU（iOS 的 Metal、Android 高通 Adreno 的 OpenCL）和 NPU（高通 Hexagon、iOS 上的 Apple 神经引擎），在无法完全使用硬件后端时会优雅降级并卸载部分图层。 |

## 面向开发者

PocketPal 是一个标准的 React Native 应用。如果你能构建 RN 项目，你就能构建 PocketPal。

### 环境准备
*   **Node.js** —— 版本已在 `.nvmrc` 中锁定（当前为 22.21.0）；运行 `nvm use` 以匹配。
*   **Yarn 1 (Classic)** —— `packageManager` 锁定为 `yarn@1.22.22`。
*   **Xcode + CocoaPods** 以及 **Ruby + Bundler**（用于 iOS / Fastlane 工具链）。
*   **Xcode 26.6 或更高版本**（需包含 Metal 工具链）：`xcodebuild -downloadComponent MetalToolchain`。
*   **Android Studio + Android SDK/NDK**。

### 克隆、安装与运行
```bash
git clone https://github.com/a-ghorbani/pocketpal-ai
cd pocketpal-ai

nvm use                       # 匹配锁定的 Node 版本
yarn install                  # 安装 JS 依赖
(cd ios && pod install)       # 仅限 iOS

yarn start                    # 启动 Metro 打包服务
yarn ios                      # 构建并在 iOS 模拟器运行
yarn android                  # 构建并在 Android 模拟器运行
