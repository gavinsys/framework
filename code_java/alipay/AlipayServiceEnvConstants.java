

/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.walkingroutes.alipay;

/**
 * 支付宝服务窗环境常量（demo中常量只是参考，需要修改成自己的常量值）
 * 
 * @author taixu.zqq
 * @version $Id: AlipayServiceConstants.java, v 0.1 2014年7月24日 下午4:33:49 taixu.zqq Exp $
 */
public class AlipayServiceEnvConstants {

    /**支付宝公钥-从支付宝服务窗获取*/
    public static final String ALIPAY_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

    /**签名编码-视支付宝服务窗要求*/
    public static final String SIGN_CHARSET      = "UTF-8";

    /**字符编码-传递给支付宝的数据编码*/
    public static final String CHARSET           = "UTF-8";

    /**签名类型-视支付宝服务窗要求*/
    public static final String SIGN_TYPE         = "RSA";
    
    
    public static final String PARTNER           = "2088021993257543";

    /** 服务窗appId  */
    //TODO !!!! 注：该appId必须设为开发者自己的服务窗id  这里只是个测试id
    public static final String APP_ID            = "2015110900735029";

    //开发者请使用openssl生成的密钥替换此处  请看文档：https://fuwu.alipay.com/platform/doc.htm#2-1接入指南
    //TODO !!!! 注：该私钥为测试账号私钥  开发者必须设置自己的私钥 , 否则会存在安全隐患 
    public static final String PRIVATE_KEY       = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJsC/wcVE8o1L4OV2wLT1IC2S9aeVrgqvRmGl20O78nfilDA6ci0BmZm7W0gWOvrxrxSY5YY6m99v+761OYzMglZaHUsrea7aVjqfG8eJat27bfYGel65pvJnnmZwVqXqGCufBEHmtpJF/3Gruk4S3AuAVdVWPs3nxkl1Qyr9EWzAgMBAAECgYBHCvR+9nX/cqi4EfIyNYtrAiF311G/Tjckf77eyL21M7JcmE2NIpTcUcf2/6BBX+nRXaEupXSsbGGO0X7JkiUX1So96JCRyaWgJkWkJYzhmboaHZtbW1bDujCI0qJqsIEDkLZZlplBQke5j3q00Z0OMkd+aDWpL+NyUr/+U6jNwQJBAMz7JMOuINkbonybK7EKdCSNF/AughXzFzvA7zSVAinp1inD/XKRFer0/fMbTY/xnOGiKAXPbWVO7BOyBRBduvkCQQDBl/ABx700pErEJxBlkCkMhkxQVQOVNUGFNCTOjzPBIwuCayT2EzY9iTWzBBxJHWGSWWIcZepeh6jgNK4vsWULAkEArqk0+3QNU1Hj029Y/GMe8eIzypmmiNN5MZZFfTWXAWXZ8f6poIyV833MQfE61vOWrQBCQCkuWwuzx1iCTh6/6QJBAIqk3I751CgaLS1zaIvEBZHhsE+rwsgd/tF9uYpxBjigd4KsZykQMz04wct1+yflsdYUy23ZE1MlxqzjLYgcfGcCQQCZTEFee5eCYhbyI1Lk7UXFRQINlXhw91hceuN367H1uJIqtIMbJyor+qAaDMRQEpWyiOvC+REglN8K5JkbPaCP";

    //TODO !!!! 注：该公钥为测试账号公钥  开发者必须设置自己的公钥 ,否则会存在安全隐患
    public static final String PUBLIC_KEY        = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

    /**支付宝网关*/
    public static final String ALIPAY_GATEWAY    = "https://openapi.alipay.com/gateway.do";

    /**授权访问令牌的授权类型*/
    public static final String GRANT_TYPE        = "authorization_code";
}