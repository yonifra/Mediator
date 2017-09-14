package com.cryprocodes.mediator.Utils;

/**
 * Created by yonifra on 12/9/17.
 */

public class TorrentNameParser {
    public String EscapeRegex(String regex) {
        return regex.replaceAll("[\\-\\[\\]{}()*+?.,\\\\\\^$|#\\s]","\\\\$&");
    }
}