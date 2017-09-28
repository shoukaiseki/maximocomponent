package org.shoukaiseki.webclient.mxpassword;

public class PropertiesUtil
{
  public static String CRYPTOX_ALGORITHM = "DESede";
  public static String CRYPTOX_MODE = "CBC";
  public static String CRYPTOX_PADDING = "PKCS5Padding";
  public static String CRYPTOX_KEY = "j3*9vk0e8rjvc9fj(*KFikd#";
  public static String CRYPTOX_SPEC = "kE*(RKc%";
  public static String CRYPTOX_MODULUS = "";
  public static String CRYPTOX_PROVIDER = null;
  private String algorithm;
  private String mode;
  private String padding;
  private String key;
  private String spec;
  private String modulus;
  private String provider;

  public PropertiesUtil()
  {
      algorithm = CRYPTOX_ALGORITHM;
      mode = "CBC";
      padding = "PKCS5Padding";
      key = "Sa#qk5usfmMI-@2dbZP9`jL3";
      spec = "beLd7$lB";
      modulus = CRYPTOX_MODULUS;
      provider = CRYPTOX_PROVIDER;
    }

  public void setAlgorithm(String algorithm)
  {
    this.algorithm = algorithm;
  }

  public void setMode(String mode) {
    this.mode = mode;
  }

  public void setPadding(String padding) {
    this.padding = padding;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public void setSpec(String spec) {
    this.spec = spec;
  }

  public void setModulus(String modulus) {
    this.modulus = modulus;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getAlgorithm() {
    return this.algorithm;
  }

  public String getMode() {
    return this.mode;
  }

  public String getPadding() {
    return this.padding;
  }

  public String getKey() {
    return this.key;
  }

  public String getSpec() {
    return this.spec;
  }

  public String getModulus() {
    return this.modulus;
  }

  public String getProvider() {
    return this.provider;
  }
}