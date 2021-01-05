package com.example.cloudapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.util.Log;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

//import com.google.zxing.integration.android.IntentIntegrator;
//import com.google.zxing.integration.android.IntentResult;

public final class Utils {
    public static final String MIPS_PACKAGE_NAME_1 = "com.neldtv.mips";

    public static final String MIPS_PACKAGE_NAME_2 = "com.gate.mips";

    private static Point screenSize;

    public static void shutdownDevice()
    {
        try {
            Process proc = Runtime.getRuntime()
                    .exec(new String[]{ "su", "-c", "reboot -p" });
            proc.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void restartDevice()
    {
        try {
            Process proc = Runtime.getRuntime()
                    .exec(new String[]{ "su", "-c", "reboot" });
            proc.waitFor();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:",b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
            Log.e(ex.getMessage(), "getMacAddr: ");
        }
        return "02:00:00:00:00:00";
    }

    String post(String url, String json) throws IOException {
        MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static final float celsiusToFahrenheit(float paramFloat) {
        return (float)(new BigDecimal(String.valueOf(paramFloat * 9 / 5 + 32))).setScale(1, 4).doubleValue();
    }

    public static final String extractNumbersFromString(String paramString) {
        if (paramString != null) {
            CharSequence charSequence = paramString;
            StringBuilder stringBuilder = new StringBuilder();
            int i = 0;
            int j = charSequence.length();
            while (i < j) {
                char c = charSequence.charAt(i);
                if (Character.isDigit(c))
                    stringBuilder.append(c);
                i++;
            }
            charSequence = stringBuilder.toString();
            Intrinsics.checkExpressionValueIsNotNull(charSequence, "filterTo(StringBuilder(), predicate).toString()");
            return (String)charSequence;
        }
        return null;
    }

    public static final String getCompleteUrl(String paramString1, String paramString2) {
        Intrinsics.checkParameterIsNotNull(paramString1, "host");
        Intrinsics.checkParameterIsNotNull(paramString2, "port");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://");
        stringBuilder.append(paramString1);
        stringBuilder.append(':');
        stringBuilder.append(paramString2);
        return stringBuilder.toString();
    }

    public static final String getIPAddress(boolean paramBoolean) {
        try {
            ArrayList<NetworkInterface> arrayList = Collections.list(NetworkInterface.getNetworkInterfaces());

            Iterator<NetworkInterface> iterator = arrayList.iterator();
            while (true) {
                if (iterator.hasNext()) {
                    ArrayList<InetAddress> arrayList1 = Collections.list(((NetworkInterface)iterator.next()).getInetAddresses());
                    Intrinsics.checkExpressionValueIsNotNull(arrayList1, "Collections.list(intf.inetAddresses)");
                    for (InetAddress inetAddress : arrayList1) {
                        if (!inetAddress.isLoopbackAddress()) {
                            int i ;
                            String str = inetAddress.getHostAddress();
//                            Intrinsics.checkExpressionValueIsNotNull(str, "sAddr");
//                            i = StringsKt.indexOf$default(str, '%', 0, false, 6, null);
                            if (StringsKt.indexOf(str, ':', 0, false) < 0) {
                                i = 1;
                            } else {
                                i = 0;
                            }
                            if (paramBoolean) {
                                if (i > 0)
                                    return str;
                                continue;
                            }
                            if (i == 0) {
                                i = StringsKt.indexOf(str, '%', 0, false);
                                if (i < 0) {
                                    String str2 = str.toUpperCase();
                                    Intrinsics.checkExpressionValueIsNotNull(str2, "(this as java.lang.String).toUpperCase()");
                                    return str2;
                                }
                                String str1 = str.substring(0, i);
                                Intrinsics.checkExpressionValueIsNotNull(str1, "(this as java.lang.StrinendIndex)");
                                if (str1 != null) {
                                    str1 = str1.toUpperCase();
                                    Intrinsics.checkExpressionValueIsNotNull(str1, "(this as java.lang.String).toUpperCase()");
                                    return str1;
                                }
                                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
                            }
                        }
                    }
                    continue;
                }
                return "0.0.0.0";
            }
        } catch (Exception exception) {}
        return "0.0.0.0";
    }

    public static final String getSHA512(String paramString) {
        Intrinsics.checkParameterIsNotNull(paramString, "input");
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        Intrinsics.checkExpressionValueIsNotNull(messageDigest, "MessageDigest.getInstance(\"SHA-512\")");
        byte[] arrayOfByte = paramString.getBytes(Charsets.UTF_8);
        Intrinsics.checkExpressionValueIsNotNull(arrayOfByte, "(this as java.lang.String).getBytes(charset)");
        String str = (new BigInteger(1, messageDigest.digest(arrayOfByte))).toString(16);
        Intrinsics.checkExpressionValueIsNotNull(str, "no.toString(16)");
        while (str.length() < 32) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append('0');
            stringBuilder.append(str);
            str = stringBuilder.toString();
        }
        return str;
    }

    public static final Point getScreenSize() {
        return screenSize;
    }

    public static final Point getScreenSize(Context paramContext) {
        Intrinsics.checkParameterIsNotNull(paramContext, "ctx");
        if (screenSize == null) {
            screenSize = new Point();
            Object object = paramContext.getSystemService( Context.WINDOW_SERVICE);
            if (object != null) {
                ((WindowManager)object).getDefaultDisplay().getSize(screenSize);
            } else {
                throw new TypeCastException("null cannot be cast to non-null type android.view.WindowManager");
            }
        }
        return screenSize;
    }

    public static final List<String> getUSBStoragePath() {
        File file = new File("/storage");
        if (!file.exists())
            return null;
        File[] arrayOfFile = file.listFiles();
        if (arrayOfFile != null) {
            ArrayList<String> arrayList = new ArrayList();
            int j = arrayOfFile.length;
            for (int i = 0; i < j; i++) {
                File file1 = arrayOfFile[i];
                Intrinsics.checkExpressionValueIsNotNull(file1, "file");
                String str = file1.getPath();
                Intrinsics.checkExpressionValueIsNotNull(str, "path");
                CharSequence charSequence = str;
                if (!StringsKt.contains(charSequence, "emulated", false)
                        && !StringsKt.contains(charSequence, "sdcard", false) &&
                        !StringsKt.contains(charSequence, "self", false))
                    arrayList.add(str);
            }
            return (arrayList.size() == 0) ? null : arrayList;
        }
        return null;
    }


    public static final void openMipsApp(Context paramContext) {
        Intrinsics.checkParameterIsNotNull(paramContext, "context");
        PackageManager packageManager = paramContext.getPackageManager();
        Intent intent2 = packageManager.getLaunchIntentForPackage("com.neldtv.mips");
        Intent intent1 = intent2;
        if (intent2 == null)
            intent1 = packageManager.getLaunchIntentForPackage("com.gate.mips");
        if (intent1 != null)
            paramContext.startActivity(intent1);
    }


    public static final void setScreenSize(Point paramPoint) {
        screenSize = paramPoint;
    }

    public static final void setTextSizeMedium(CheckBox paramCheckBox, Context paramContext) {
        Intrinsics.checkParameterIsNotNull(paramCheckBox, "$this$setTextSizeMedium");
        Intrinsics.checkParameterIsNotNull(paramContext, "ctx");
        Point point = getScreenSize(paramContext);
        if (point != null)
            paramCheckBox.setTextSize(0, (float)(point.x * 0.04D));
    }

    public static final void setTextSizeMedium(RadioButton paramRadioButton, Context paramContext) {
        Intrinsics.checkParameterIsNotNull(paramRadioButton, "$this$setTextSizeMedium");
        Intrinsics.checkParameterIsNotNull(paramContext, "ctx");
        Point point = getScreenSize(paramContext);
        if (point != null)
            paramRadioButton.setTextSize(0, (float)(point.x * 0.04D));
    }

    public static final void setTextSizeMedium(TextView paramTextView, Context paramContext) {
        Intrinsics.checkParameterIsNotNull(paramTextView, "$this$setTextSizeMedium");
        Intrinsics.checkParameterIsNotNull(paramContext, "ctx");
        Point point = getScreenSize(paramContext);
        if (point != null)
            paramTextView.setTextSize(0, (float)(point.x * 0.04D));
    }

    public static final void setTextSizeMedium(TextInputEditText paramTextInputEditText, Context paramContext) {
        Intrinsics.checkParameterIsNotNull(paramTextInputEditText, "$this$setTextSizeMedium");
        Intrinsics.checkParameterIsNotNull(paramContext, "ctx");
        Point point = getScreenSize(paramContext);
        if (point != null)
            paramTextInputEditText.setTextSize(0, (float)(point.x * 0.04D));
    }

    public static final String trimLeadingZeros(String paramString) {
        Intrinsics.checkParameterIsNotNull(paramString, "source");
        return StringsKt.replaceFirst(paramString, "^0+(?!$)", "", false);
    }

}