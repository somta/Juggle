const fs = require('fs');
const path = require('path');

function createEnvLocal () {
  const configPath = path.join(__dirname, '../.env');
  const localPath = path.join(__dirname, '../.env.local');
  if (!fs.existsSync(localPath)) {
    fs.copyFileSync(configPath, localPath);
  }
}

createEnvLocal();
