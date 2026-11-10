import TcpSocket from 'react-native-tcp-socket';
import { handleConnection, ConnectionContext, OpenConnection } from './connection'; // 确保路径正确

let serverInstance: any = null;

export const startLocalServer = (
  port: number = 8083, // 👈 端口在这里改成了 8083
  apiKey?: string,
  getServedModelId: () => string | undefined = () => 'local-model',
) => {
  if (serverInstance) {
    console.log('Server is already running');
    return;
  }

  // 初始化上下文 (满足官方 connection.ts 的要求)
  let admittedCount = 0;
  const context: ConnectionContext = {
    openConnections: new Set<OpenConnection>(),
    getApiKey: () => apiKey,
    getServedModelId,
    listenerStartedAt: Math.floor(Date.now() / 1000),
    admit: () => {
      if (admittedCount >= 2) return false; // 官方限制最大并发2个
      admittedCount++;
      return true;
    },
    release: () => {
      admittedCount--;
    },
  };

  // 创建 TCP 服务器
  serverInstance = TcpSocket.createServer((socket) => {
    console.log('New connection received');
    // 直接交给官方的 handler 处理
    handleConnection(socket as any, context);
  });

  // 🌟 核心：绑定到 0.0.0.0 允许局域网访问！
  serverInstance.listen({ port, host: '0.0.0.0' }, () => {
    console.log(`Local API Server listening on http://0.0.0.0:${port}`);
  });

  serverInstance.on('error', (error: any) => {
    console.error('Server error:', error);
  });
};

export const stopLocalServer = () => {
  if (serverInstance) {
    serverInstance.close();
    serverInstance = null;
    console.log('Server stopped');
  }
};
