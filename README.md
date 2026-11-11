**[English](README.md) | [简体中文](README_zh.md)**

# PocketPal AI (LAN-Enhanced Custom Build)

A private AI assistant that runs entirely on your phone. **This repository is a custom build that unlocks the hidden Local API Server, adds background keep-alive, and implements smart request cleaning.**

No account, no cloud dependency, and no internet required to run large language models on your phone. It also serves as an OpenAI-compatible API within your local network (LAN) for other devices (PCs, tablets) to utilize the phone's compute.

> ⚠️ **Disclaimer**: This is a personal, modified version for learning and tinkering. **It is NOT uploaded to any app stores (App Store / Google Play)**. Please do not search for it in app stores. To install, please compile it yourself via GitHub Actions or download the build artifact.

---

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

## 🧠 Upstream Core Features

* **On-device Chat** — Run GGUF language models completely offline (Gemma, Qwen, Phi, Llama, etc.).
* **TTS & Voice Input** — Local neural voice synthesis and Whisper speech recognition.
* **Pals** — Create personalized assistants with custom system prompts and personalities.
* **Hugging Face Integration** — Search and download models directly from the HF Hub.
* **Hardware Acceleration** — Support for CPU, GPU (Metal / OpenCL), and NPU (Qualcomm Hexagon, requires building from source).

---

## 📄 License

Licensed under the [MIT License](LICENSE). Forked from the original author [a-ghorbani/pocketpal-ai](https://github.com/a-ghorbani/pocketpal-ai).
