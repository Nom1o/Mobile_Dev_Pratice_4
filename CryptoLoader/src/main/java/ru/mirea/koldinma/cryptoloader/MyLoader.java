package ru.mirea.koldinma.cryptoloader;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class MyLoader extends AsyncTaskLoader<String> {
    private String firstName;
    byte[] cryptText;
    byte[] key;
    SecretKey originalKey;
    public static final String ARG_WORD = "word";
    public static final String ARG_KEY = "key";
    public static final String ARG_TEXT = "text";

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args.getString(ARG_WORD) != null ) {
            firstName = args.getString(ARG_WORD);
        }
        if (args.getByteArray(ARG_TEXT) !=null) {
            cryptText = args.getByteArray(ARG_TEXT);
            key = args.getByteArray(ARG_KEY);
            originalKey = new SecretKeySpec(key, 0, key.length, "AES");
        }

    }
    public static String decryptMsg(byte[] cipherText, SecretKey secret){
        /* Decrypt the message */
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secret);
            return new String(cipher.doFinal(cipherText));
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                 | BadPaddingException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public String loadInBackground() {
// emulate long-running operation

        SystemClock.sleep(5000);

        if (cryptText !=null) {
            return decryptMsg(cryptText, originalKey);

        }
        else
            return firstName;
    }
}