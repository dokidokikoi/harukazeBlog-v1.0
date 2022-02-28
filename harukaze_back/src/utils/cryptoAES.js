import CryptoJs from 'crypto-js'

export default {
    /**
     * 加密
     */
    encrypt(word) {
      let key = CryptoJs.enc.Utf8.parse("JcRYQ4yt3i^9%mdt");
      let srcs = CryptoJs.enc.Utf8.parse(word);
      let encrypted = CryptoJs.AES.encrypt(srcs, key, {
        mode: CryptoJs.mode.ECB,
        padding: CryptoJs.pad.Pkcs7
      });
      return encrypted.toString();
    },
   
    /**
     * 解密
     */
    decrypt(word) {
      let key = CryptoJs.enc.Utf8.parse("JcRYQ4yt3i^9%mdt");
      let decrypt = CryptoJs.AES.decrypt(word, key, {
        mode: CryptoJs.mode.ECB,
        padding: CryptoJs.pad.Pkcs7
      });
      return CryptoJs.enc.Utf8.stringify(decrypt).toString();
    }
}