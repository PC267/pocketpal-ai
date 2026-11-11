**[English](README.md) | [简体中文](README_zh.md)**

<div align="center">

<img src="src/assets/pocketpal-dark-v2.png" alt="PocketPal AI logo" width="120" />

# PocketPal AI (LAN-Enhanced Custom Build)

**A private AI assistant that runs entirely on your phone, optimized for local networking.**

Chat with language models, give them a voice, and let them use tools — all on-device. No account, no cloud, no internet required. **This repository is a custom build that unlocks the hidden Local API Server, adds background keep-alive, and implements smart request cleaning.**

<a href="https://pocketpal.dev/"><strong>pocketpal.dev</strong></a> ·
<a href="https://pocketpal.dev/leaderboard">Leaderboard</a> ·
<a href="https://palshub.ai/">PalsHub</a> ·
<a href="https://github.com/a-ghorbani/pocketpal-ai/discussions">Discussions</a>

<br/>

[![Build Status](https://img.shields.io/github/actions/workflow/status/PC267/pocketpal-ai/CPU&GPU-Universal-apk.yml?branch=main&label=Build&logo=github)](https://github.com/PC267/pocketpal-ai/actions)
[![Latest release](https://img.shields.io/github/v/release/PC267/pocketpal-ai?sort=semver)](https://github.com/PC267/pocketpal-ai/releases)
[![License: MIT](https://img.shields.io/github/license/PC267/pocketpal-ai)](LICENSE)
[![Stars](https://img.shields.io/github/stars/PC267/pocketpal-ai)](https://github.com/PC267/pocketpal-ai/stargazers)

</div>

---

> ⚠️ **Disclaimer**: This is a personal, modified version for learning and tinkering. **It is NOT uploaded to any app stores (App Store / Google Play)**. Please do not search for it in app stores. To install, please compile it yourself via GitHub Actions or download the build artifact.

## ✨ Custom Enhancements (vs. Upstream)

To achieve the ultimate local network sharing experience, we have made the following deep modifications:

* 🔓 **Fully Unlocked Local API Server**: Removed the original "Plus" paywall requirement to enable the API server. It is now completely free.
* 🔌 **Custom Default Port**: Changed the default API listening port to **`8083`** to avoid conflicts with common ports.
* 🧹 **Smart API Compatibility Cleaning**: Built-in request sanitization logic that **forcibly strips `tools` parameters** sent by third-party clients and fixes malformed `messages` roles. Perfectly compatible with `DeepSeek Harness`, `Chatbox`, `NextChat`, etc., eliminating 400/500 errors.
* ✂️ **Targeted Context Truncation**: Added smart cleaning logic to automatically trim excessively long system prompts, preventing the local model from instantly crashing due to "Context is full" caused by verbose agent system prompts.
* 🔋 **Foreground Service Keep-Alive**: Integrated Android Foreground Service. A persistent notification keeps the API interface alive when the screen is off or the app is in the background, turning your phone into a 24/7 micro-server.
* ⚡ **Fast CI/CD Builds**: Includes a GitHub Actions workflow (skips compiling C++ from source) to build a small, ready-to-use APK in ~10 minutes.

---

## 📥 How to Get This Custom APK

Since this version is not on app stores, you can get the installation package as follows:

**Method 1: Auto-build via GitHub Actions (Recommended)**
1. Click the **Actions** tab at the top of this repository.
2. Select `Build PocketPal (CPU/GPU Universal)` on the left (suitable for most phones, including MediaTek, Kirin, and most Snapdragon chips).
3. Click the **Run workflow** button on the right.
4. Wait for ~10 minutes. Download `PocketPal-Universal-APK.zip` from the **Artifacts** section at the bottom of the build page.
5. Unzip and install `app-prod-debug.apk`.

**Method 2: Local Build**
If you are familiar with React Native development, clone this repo, run `yarn install`, and use `yarn android` to build and run directly.

---

## 📱 How to Use the Local API

<div align="center">
  <img src="assets/images and logos/stack-diagram-dark.png" alt="PocketPal AI on-device stack — UI & Tool Use → Bridging → Engine → Hardware" width="100%">
  <p><i>手机端 AI 运行架构</i></p>
</div>

1. Download and load any GGUF model in the mobile app.
2. Go to the App Settings and scroll to the bottom to find **Local API Server**.
3. Toggle on `Serve the loaded model` and `Allow other devices on the network` (LAN).
4. The UI will display an address like `http://192.168.x.x:8083/v1`.
5. Configure your PC client (e.g., Chatbox):
   * **API Host**: Enter the URL above.
   * **API Key**: Fill in anything (e.g., `sk-1234`).
   * **Model Name**: Choose any, or enter the model name from your phone (the server overrides this automatically).
6. Start chatting! You can even call this endpoint via code on your PC.

---

## 🧠 Upstream Core Features & Screenshots

PocketPal is a four-layer stack, from the silicon up to the chat UI. Each layer has one job, and the dependency direction is strictly top-down — the JS app talks to native bridges, bridges talk to inference engines, engines target hardware backends.

<details>
<summary><strong>📥 Download & load a model</strong></summary>

<br/>

1. Open the app and tap the **Menu** (☰), then go to **Models**.
2. Pick a model from the list and tap **Download**, or tap **+** to add one from Hugging Face or local storage.
3. From Hugging Face, search GGUF models and choose a quantization that fits your device's memory and storage — download now or bookmark for later.
4. After downloading, tap **Load** (or use the chevron icon left of the chat input to load right from the chat screen).

<img src="assets/images and logos/Download_models.png" alt="Download Models" width="100%">
</details>

<details>
<summary><strong>💬 Chat</strong></summary>

<br/>

1. Make sure a model is loaded.
2. Open the **Chat** page and start talking.
3. The screen stays awake during inference and deactivates when idle.
4. **Copy** a full response with the copy icon, or long-press a paragraph to copy just that.
5. **Edit** any of your messages with a long-press — the AI regenerates from your change. Hit **retry** for a fresh answer, optionally with a different model.

<img src="assets/images and logos/Chat.png" alt="Chat" width="83%">
</details>

<details>
<summary><strong>🎭 Pals & PalsHub</strong></summary>

<br/>

Create personalized assistants:
- **Assistant Pal** — pick a default model, set a system prompt (write it yourself or have the app generate one), and customize the chat input color.
- **Roleplay Pal** — everything above, plus location, the AI's role, and other contextual parameters.

Switch personas with the Pal picker on the chat page. Browse **[PalsHub](https://palshub.ai/)** in-app to discover community Pals, including premium ones via in-app purchase.

<img src="assets/images and logos/Pals.png" alt="Assistant Pal" width="100%">
<p><em>Creating a cocktail-recipe assistant</em></p>
</details>

<details>
<summary><strong>📊 Benchmark your device</strong></summary>

<br/>

1. Open the **Benchmark** page.
2. Run performance tests to compare speed and efficiency across models.
3. Review tokens/sec and memory usage.
4. Optionally share your results to the [AI Phone Leaderboard](https://pocketpal.dev/leaderboard).

<img src="assets/images and logos/Benchmark.png" alt="Benchmark" width="100%">
</details>

<details>
<summary><strong>🔑 Set up a Hugging Face token (for gated models)</strong></summary>

<br/>

1. Create an access token in your Hugging Face account ([docs](https://huggingface.co/docs/hub/en/security-tokens)).
2. In PocketPal, go to **Settings → Set Token**, paste it, and save.

<img src="assets/images and logos/Token_in_pocketpal.png" alt="Token setup" width="66%">
</details>

---

## 📄 License

Licensed under the [MIT License](LICENSE). Forked from the original author [a-ghorbani/pocketpal-ai](https://github.com/a-ghorbani/pocketpal-ai).

<div align="center">
<br/>

Made with ❤️ for people who want AI that stays on their phone.

<br/>

<sub>If this custom build is useful to you, consider giving it a ⭐ — it helps others find the project.</sub>

</div>
