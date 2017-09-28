package org.shoukaiseki.webclient.mxpassword;

import java.lang.reflect.Constructor;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class MXCipherX {

	String algorithm;
	  String mode;
	  String padding;
	  String key;
	  String spec;
	  String modulus;
	  private Cipher cipherEncrypt;
	  String transformation;
	  SecretKey secretKey;
	  IvParameterSpec ivSpec;
	  PBEParameterSpec pbeParamSpec;
	  SecretKeySpec secretkeySpec;
	  PublicKey publicKey;
	  PrivateKey privateKey;
	  boolean nonSunProviders;
	  Provider providerClass;
	  int padLen;

	  protected MXCipherX()
	  {
	    this.algorithm = PropertiesUtil.CRYPTOX_ALGORITHM;
	    this.mode = PropertiesUtil.CRYPTOX_MODE;
	    this.padding = PropertiesUtil.CRYPTOX_PADDING;
	    this.key = PropertiesUtil.CRYPTOX_KEY;
	    this.spec = PropertiesUtil.CRYPTOX_SPEC;
	    this.modulus = PropertiesUtil.CRYPTOX_MODULUS;
	    this.cipherEncrypt = null;
	    this.transformation = null;
	    this.secretKey = null;
	    this.ivSpec = null;
	    this.pbeParamSpec = null;
	    this.secretkeySpec = null;
	    this.publicKey = null;
	    this.privateKey = null;
	    this.nonSunProviders = false;
	    this.providerClass = null;
	    this.padLen = 8;
	  }

	  /**
	 * @param isEncrypt true:加密,false:解密
	 * @param pu
	 */
	public MXCipherX(boolean isEncrypt, PropertiesUtil pu) {
	    this.algorithm = PropertiesUtil.CRYPTOX_ALGORITHM;
	    this.mode = PropertiesUtil.CRYPTOX_MODE;
	    this.padding = PropertiesUtil.CRYPTOX_PADDING;
	    this.key = PropertiesUtil.CRYPTOX_KEY;
	    this.spec = PropertiesUtil.CRYPTOX_SPEC;
	    this.modulus = PropertiesUtil.CRYPTOX_MODULUS;

	    this.cipherEncrypt = null;
	    this.transformation = null;
	    this.secretKey = null;
	    this.ivSpec = null;
	    this.pbeParamSpec = null;
	    this.secretkeySpec = null;
	    this.publicKey = null;
	    this.privateKey = null;
	    this.nonSunProviders = false;
	    this.providerClass = null;
	    this.padLen = 8;

	    String algTest = pu.getAlgorithm();
	    String modeTest = pu.getMode();
	    String paddingTest = pu.getPadding();
	    String keyTest = pu.getKey();
	    String specTest = pu.getSpec();
	    String modTest = pu.getModulus();
	    String providerTest = pu.getProvider();
	    init(isEncrypt, algTest, modeTest, paddingTest, keyTest, specTest, modTest, providerTest);
	  }

	  public MXCipherX(boolean isEncrypt, String algTest, String modeTest, String paddingTest, String keyTest, String specTest) {
	    this.algorithm = PropertiesUtil.CRYPTOX_ALGORITHM;
	    this.mode = PropertiesUtil.CRYPTOX_MODE;
	    this.padding = PropertiesUtil.CRYPTOX_PADDING;
	    this.key = PropertiesUtil.CRYPTOX_KEY;
	    this.spec = PropertiesUtil.CRYPTOX_SPEC;
	    this.modulus = PropertiesUtil.CRYPTOX_MODULUS;

	    this.cipherEncrypt = null;
	    this.transformation = null;
	    this.secretKey = null;
	    this.ivSpec = null;
	    this.pbeParamSpec = null;
	    this.secretkeySpec = null;
	    this.publicKey = null;
	    this.privateKey = null;
	    this.nonSunProviders = false;
	    this.providerClass = null;
	    this.padLen = 8;
	    init(isEncrypt, algTest, modeTest, paddingTest, keyTest, specTest, null, null);
	  }

	  public MXCipherX(boolean isEncrypt, String algTest, String modeTest, String paddingTest, String keyTest, String specTest, String modTest, String providerTest) {
	    this.algorithm = PropertiesUtil.CRYPTOX_ALGORITHM;
	    this.mode = PropertiesUtil.CRYPTOX_MODE;
	    this.padding = PropertiesUtil.CRYPTOX_PADDING;
	    this.key = PropertiesUtil.CRYPTOX_KEY;
	    this.spec = PropertiesUtil.CRYPTOX_SPEC;
	    this.modulus = PropertiesUtil.CRYPTOX_MODULUS;

	    this.cipherEncrypt = null;
	    this.transformation = null;
	    this.secretKey = null;
	    this.ivSpec = null;
	    this.pbeParamSpec = null;
	    this.secretkeySpec = null;
	    this.publicKey = null;
	    this.privateKey = null;
	    this.nonSunProviders = false;
	    this.providerClass = null;
	    this.padLen = 8;
	    init(isEncrypt, algTest, modeTest, paddingTest, keyTest, specTest, modTest, providerTest);
	  }

	  protected void init(boolean isEncrypt, String algTest, String modeTest, String paddingTest, String keyTest, String specTest, String modTest, String providerTest) {
	    try {
	      if ((providerTest != null) && (!providerTest.equals(""))) {
	        Class c = Class.forName(providerTest);
	        Class[] paramTypes = new Class[0];
	        Constructor ctor = c.getConstructor(paramTypes);
	        Object[] params = new Object[0];
	        this.providerClass = ((Provider)ctor.newInstance(params));
	        Security.addProvider(this.providerClass);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	      return;
	    }
	    Provider[] provs = Security.getProviders();
	    for (int xx = 0; xx < provs.length; xx++) {
	      if (provs[xx].getName().toUpperCase().startsWith("SUN"));
	      this.nonSunProviders = true;
	    }

	    validateParams(algTest, modeTest, paddingTest, keyTest, specTest, modTest);
	    this.transformation = this.algorithm;
	    if ((this.mode != null) && (!this.mode.equals("")) && (this.padding != null) && (!this.padding.equals("")))
	      this.transformation = (this.transformation + "/" + this.mode + "/" + this.padding);
	    try {
	      this.cipherEncrypt = buildCipher(isEncrypt);
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }

	  Cipher buildCipher(boolean encrypt) throws Exception
	  {
	    Cipher cipher = null;
	    int cryptMode = 1;
	    if (!encrypt)
	      cryptMode = 2;
	    if ((this.algorithm.equals("DESede")) || (this.algorithm.equals("TripleDES"))) {
	      if ((this.secretKey == null) || (this.ivSpec == null)) {
	        DESedeKeySpec keyspec = new DESedeKeySpec(this.key.getBytes());
	        SecretKeyFactory factory = SecretKeyFactory.getInstance(this.algorithm);
	        this.secretKey = factory.generateSecret(keyspec);
	        this.ivSpec = new IvParameterSpec(this.spec.getBytes());
	      }
	      if (this.providerClass == null)
	        cipher = Cipher.getInstance(this.transformation);
	      else
	        cipher = Cipher.getInstance(this.transformation, this.providerClass);
	      if (this.transformation.indexOf("ECB") < 0)
	        cipher.init(cryptMode, this.secretKey, this.ivSpec);
	      else
	        cipher.init(cryptMode, this.secretKey);
	    }
	    else if (this.algorithm.equals("DES")) {
	      if ((this.secretKey == null) || (this.ivSpec == null)) {
	        DESKeySpec keyspec = new DESKeySpec(this.key.getBytes());
	        SecretKeyFactory factory = SecretKeyFactory.getInstance(this.algorithm);
	        this.secretKey = factory.generateSecret(keyspec);
	        this.ivSpec = new IvParameterSpec(this.spec.getBytes());
	      }
	      if (this.providerClass == null)
	        cipher = Cipher.getInstance(this.transformation);
	      else
	        cipher = Cipher.getInstance(this.transformation, this.providerClass);
	      if (this.transformation.indexOf("ECB") < 0)
	        cipher.init(cryptMode, this.secretKey, this.ivSpec);
	      else
	        cipher.init(cryptMode, this.secretKey);
	    }
	    else if (this.algorithm.startsWith("PBEWith")) {
	      if ((this.secretKey == null) || (this.pbeParamSpec == null)) {
	        this.pbeParamSpec = new PBEParameterSpec(this.spec.getBytes(), 20);
	        PBEKeySpec pbeKeySpec = new PBEKeySpec(this.spec.toCharArray());
	        SecretKeyFactory keyFac = SecretKeyFactory.getInstance(this.algorithm);
	        this.secretKey = keyFac.generateSecret(pbeKeySpec);
	      }
	      if (this.providerClass == null)
	        cipher = Cipher.getInstance(this.transformation);
	      else
	        cipher = Cipher.getInstance(this.transformation, this.providerClass);
	      cipher.init(cryptMode, this.secretKey, this.pbeParamSpec);
	    }
	    else if (this.algorithm.equals("RSA")) {
	      if ((this.publicKey == null) || (this.privateKey == null)) {
	        KeyFactory fac = KeyFactory.getInstance("RSA", this.providerClass);
	        this.publicKey = fac.generatePublic(new RSAPublicKeySpec(new BigInteger(this.modulus), new BigInteger(this.key)));
	        this.privateKey = fac.generatePrivate(new RSAPrivateKeySpec(new BigInteger(this.modulus), new BigInteger(this.spec)));
	      }
	      if (this.providerClass == null)
	        cipher = Cipher.getInstance(this.transformation);
	      else
	        cipher = Cipher.getInstance(this.transformation, this.providerClass);
	      if (encrypt)
	        cipher.init(cryptMode, this.publicKey);
	      else
	        cipher.init(cryptMode, this.privateKey);
	    } else {
	      if (this.secretkeySpec == null) {
	        int padLen = this.algorithm.equals("SKIPJACK") ? 10 : 16;
	        byte[] byteArray = this.spec.getBytes();
	        byteArray = pad(byteArray, padLen);
	        this.secretkeySpec = new SecretKeySpec(byteArray, this.algorithm);
	      }
	      if (this.providerClass == null)
	        cipher = Cipher.getInstance(this.transformation);
	      else
	        cipher = Cipher.getInstance(this.transformation, this.providerClass);
	      cipher.init(cryptMode, this.secretkeySpec);
	    }
	    return cipher;
	  }

	  private boolean validateParams(String algTest, String modeTest, String paddingTest, String keyTest, String specTest, String modTest) {
	    if ((algTest != null) && (!algTest.equals("")))
	      this.algorithm = algTest;
	    if ((modeTest != null) && (!modeTest.equals("")))
	      this.mode = modeTest;
	    if ((paddingTest != null) && (!paddingTest.equals("")))
	      this.padding = paddingTest;
	    if ((keyTest != null) && (!keyTest.equals("")))
	      this.key = keyTest;
	    if ((specTest != null) && (!specTest.equals("")))
	      this.spec = specTest;
	    if ((modTest != null) && (!modTest.equals("")))
	      this.modulus = modTest;
	    if (this.algorithm == null)
	      return false;
	    if ((this.algorithm.equals("AES")) || (this.algorithm.equals("Serpent")) || (this.algorithm.equals("MARS")) || (this.algorithm.equals("RC6")) || (this.algorithm.equals("Rijndael")) || (this.algorithm.equals("Square")) || (this.algorithm.equals("Twofish"))) {
	      this.padLen = 16;
	    }
	    else if (this.algorithm.equals("RSA"))
	      this.padLen = 0;
	    if ((!this.algorithm.equals("DES")) && (!this.algorithm.equals("DESede")) && (!this.algorithm.equals("AES"))) {
	      if ((modeTest == null) || (modeTest.equals("")))
	        this.mode = "";
	      if ((paddingTest == null) || (paddingTest.equals("")))
	        this.padding = "";
	    }
	    if (this.nonSunProviders)
	      return true;
	    if ((!this.algorithm.equals("DESede")) && (!this.algorithm.equals("DES")) && (!this.algorithm.equals("AES")) && (!this.algorithm.equals("PBEWithMD5AndDES")))
	      return false;
	    if ((this.algorithm.equals("AES")) && ((this.mode == null) || (!this.mode.equals("ECB"))))
	      return false;
	    if (this.algorithm.equals("PBEWithMD5AndDES")) {
	      if ((this.mode == null) || (!this.mode.equals("CBC")))
	        return false;
	      if ((this.padding == null) || (!this.padding.equals("PKCS5Padding")))
	        return false;
	      if (this.key.getBytes().length != 8)
	        return false;
	    }
	    if ((this.mode != null) && (!this.mode.equals("")) && (!this.mode.equals("CBC")) && (!this.mode.equals("CFB")) && (!this.mode.equals("ECB")) && (!this.mode.equals("OFB")) && (!this.mode.equals("PCBC")))
	      return false;
	    if ((this.padding != null) && (!this.padding.equals("")) && (!this.padding.equals("NoPadding")) && (!this.padding.equals("PKCS5Padding")))
	      return false;
	    if ((this.key != null) && (!this.key.equals("")) && (!this.algorithm.equals("RSA")) && (this.key.length() % 24 != 0))
	      return false;
	    if ((this.spec != null) && (!this.spec.equals("")) && (!this.algorithm.equals("RSA")) && (this.spec.length() % 8 != 0)) {
	      return false;
	    }
	    return (this.mode == null) || (!this.mode.equals("OFB")) || (this.padding == null) || (this.padding.equals("NoPadding")) || ((this.algorithm != null) && (this.algorithm.equals("DES")));
	  }

	  public synchronized String encData(String in)
	  {
	    byte[] temp = in.getBytes();
	    temp = pad(temp);
	    byte[] encryptVal = (byte[])null;
	    try {
	      encryptVal = this.cipherEncrypt.doFinal(temp);
	    } catch (Exception e) {
	      e.printStackTrace();
	      return null;
	    }
	    return EncodeUtil.Bytes2HexString(encryptVal);
	  }

	  public synchronized String decData(String in) {
	    byte[] temp = EncodeUtil.HexString2Bytes(in);
	    temp = pad(temp);
	    byte[] decryptVal = (byte[])null;
	    try {
	      decryptVal = this.cipherEncrypt.doFinal(temp);
	      String value = new String(decryptVal);
	      if (value == null)
	        return null;
	      return value.trim();
	    } catch (Exception e) {
	      e.printStackTrace();
	    }return null;
	  }

	  protected byte[] pad(byte[] in)
	  {
	    return pad(in, this.padLen);
	  }

	  protected byte[] pad(byte[] in, int padLen) {
	    if (padLen == 0)
	      return in;
	    int inlen = in.length;
	    int outlen = inlen;
	    int rem = inlen % padLen;
	    if (rem > 0)
	      outlen = inlen + (padLen - rem);
	    byte[] out = new byte[outlen];
	    for (int xx = 0; xx < inlen; xx++) {
	      out[xx] = in[xx];
	    }
	    return out;
	  }
}
