package org.shoukaiseki.webclient.mxpassword;

public class EncodeUtil {

	public static void printHexString(String hint, byte[] b)
	  {
	    System.out.print(hint);
	    for (int i = 0; i < b.length; i++) {
	      String hex = Integer.toHexString(b[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      System.out.print(hex.toUpperCase() + " ");
	    }
	    System.out.println("");
	  }

	  public static String Bytes2HexString(byte[] b)
	  {
	    if (b == null)
	      return null;
	    String ret = "";
	    for (int i = 0; i < b.length; i++) {
	      String hex = Integer.toHexString(b[i] & 0xFF);
	      if (hex.length() == 1) {
	        hex = '0' + hex;
	      }
	      ret = ret + hex.toUpperCase();
	    }
	    return ret;
	  }

	  public static byte uniteBytes(byte src0, byte src1)
	  {
	    byte _b0 = Byte.decode("0x" + new String(new byte[] { src0 })).byteValue();
	    _b0 = (byte)(_b0 << 4);
	    byte _b1 = Byte.decode("0x" + new String(new byte[] { src1 })).byteValue();
	    byte ret = (byte)(_b0 ^ _b1);
	    return ret;
	  }

	  public static byte[] HexString2Bytes(String src)
	  {
	    byte[] tmp = src.getBytes();
	    byte[] ret = new byte[tmp.length / 2];

	    for (int i = 0; i < ret.length; i++) {
	      ret[i] = uniteBytes(tmp[(i * 2)], tmp[(i * 2 + 1)]);
	    }
	    return ret;
	  }

	  public static byte[] Encode(byte[] b)
	  {
	    String s = Bytes2HexString(b);
	    return s.getBytes();
	  }

	  public static byte[] Decode(byte[] b)
	  {
	    String src = new String(b);
	    return HexString2Bytes(src);
	  }
}
