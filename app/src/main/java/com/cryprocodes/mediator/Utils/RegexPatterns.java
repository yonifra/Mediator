package com.cryprocodes.mediator.Utils;

import java.util.ArrayList;

/**
 * Created by yonifra on 13/9/17.
 */

public class RegexPatterns {
    ArrayList<PatternPair<String, String>> list = new ArrayList<>();

    public RegexPatterns() {
        InitializePatterns();
    }

    private void InitializePatterns() {
        list.add(new PatternPair<>("season", "(s?([0-9]{1,2}))[ex]"));
        list.add(new PatternPair<>("episode", "([ex]([0-9]{2})(?:[^0-9]|$))"));
        list.add(new PatternPair<>("year", "([\\[\\(]?((?:19[0-9]|20[01])[0-9])[\\]\\)]?)"));
        list.add(new PatternPair<>("resolution","([0-9]{3,4}p)"));
        list.add(new PatternPair<>("quality","((?:PPV\\.)?[HP]DTV|(?:HD)?CAM|B[DR]Rip|(?:HD-?)?TS|(?:PPV)?WEB-?DL(?: DVDRip)?|HDRip|DVDRip|DVDRIP|CamRip|W[EB]BRip|BluRay|DvDScr|hdtv|telesync)"));
        list.add(new PatternPair<>("codec","(xvid|[hx]\\.?26[45])"));
        list.add(new PatternPair<>("audio","(MP3|DD5\\.?1|Dual[\\- ]Audio|LiNE|DTS|AAC[.-]LC|AAC(?:\\.?2\\.0)?|AC3(?:\\.5\\.1)?)"));
        list.add(new PatternPair<>("group","(- ?([^-]+(?:-={[^-]+-?$)?))$"));
        list.add(new PatternPair<>("region","R[0-9]"));
        list.add(new PatternPair<>("extended","(EXTENDED(:?.CUT)?)"));
        list.add(new PatternPair<>("hardcoded","HC"));
        list.add(new PatternPair<>("proper","PROPER"));
        list.add(new PatternPair<>("repack","REPACK"));
        list.add(new PatternPair<>("container","(MKV|AVI|MP4)"));
        list.add(new PatternPair<>("widescreen","WS"));
        list.add(new PatternPair<>("website","^(\\[ ?([^\\]]+?) ?\\])"));
        list.add(new PatternPair<>("language","(rus\\.eng|ita\\.eng)"));
        list.add(new PatternPair<>("sbs","(?:Half-)?SBS"));
        list.add(new PatternPair<>("unrated","UNRATED"));
        list.add(new PatternPair<>("size","(\\d+(?:\\.\\d+)?(?:GB|MB))"));
        list.add(new PatternPair<>("3d","3D"));
    }
}
