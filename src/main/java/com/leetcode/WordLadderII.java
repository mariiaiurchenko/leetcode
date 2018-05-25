package com.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:

Only one letter can be changed at a time
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
Note:

Return an empty list if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
Example 1:

Input:
beginWord = "hit",
endWord = "cog",
wordList = ["hot","dot","dog","lot","log","cog"]

Output:
[
  ["hit","hot","dot","dog","cog"],
  ["hit","hot","lot","log","cog"]
]
Example 2:

Input:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]

Output: []

Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */
public class WordLadderII {

    public static void main(String[] args) {
        WordLadderII wordLadderII = new WordLadderII();

        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        List<List<String>> pathways = wordLadderII.findLadders(beginWord, endWord, wordList);

        beginWord = "ta";
        endWord = "if";
        wordList = Arrays.asList("ts", "sc", "ph", "ca", "jr", "hf", "to", "if", "ha", "is", "io", "cf", "ta");
        pathways = wordLadderII.findLadders(beginWord, endWord, wordList);

        beginWord = "raining";
        endWord = "cellini";
        wordList = Arrays.asList("heaping", "conning", "nipping", "wadding", "pulling", "lunging", "figging", "donning", "jamming", "coating", "foaling", "ousting", "dowsing", "busting", "penning", "lapping", "yanking", "sapping", "tasking", "rigging", "ranking", "larking", "farming", "dunging", "nutting", "gouging", "barfing", "fasting", "belting", "boiling", "boating", "dipping", "kilning", "barking", "furling", "calving", "veiling", "decking", "ricking", "salting", "lucking", "sending", "taiping", "marking", "martina", "warping", "bulking", "seaming", "topping", "larding", "jilting", "besting", "weeding", "nesting", "baiting", "jibbing", "pelting", "bushing", "garbing", "banting", "keeping", "venting", "rapping", "binning", "mulling", "smiting", "hatting", "tapping", "writing", "footing", "carding", "ratting", "bagging", "sitting", "dousing", "pinking", "testing", "passing", "gelling", "gassing", "ranging", "hefting", "vamping", "wetting", "paining", "rolling", "sinking", "yakking", "shaking", "nabbing", "licking", "sparing", "hamming", "celling", "halving", "matting", "landing", "kooking", "pinning", "hagging", "narking", "soaping", "winding", "dealing", "earring", "cunning", "moating", "skiting", "jutting", "fueling", "hooping", "guiling", "mapping", "hailing", "gutting", "firming", "bunting", "mealing", "rending", "jobbing", "pauling", "foiling", "peeking", "rollins", "lansing", "felling", "whiting", "vealing", "resting", "saltine", "earning", "purging", "mullins", "pausing", "colling", "banning", "wasting", "sealing", "gigging", "scaring", "pocking", "massing", "curring", "storing", "dinging", "handing", "pitting", "faining", "cupping", "staring", "riffing", "gowning", "hipping", "vanning", "darting", "maiming", "damping", "deaning", "bellini", "kipling", "marting", "hawking", "fending", "kicking", "beading", "curving", "wending", "yelling", "foaming", "rifting", "surging", "gaining", "stoking", "panging", "winking", "nursing", "oinking", "looking", "tolling", "bailing", "tanking", "hacking", "warming", "cooping", "wanting", "rotting", "kinking", "bugging", "purling", "wincing", "joining", "belling", "wilting", "tensing", "fellini", "wilding", "binding", "bugling", "sagging", "nagging", "binging", "tatting", "cellini", "silting", "belying", "ripping", "crating", "slaking", "killing", "hurting", "running", "harming", "banding", "rinking", "staying", "touting", "hasting", "melting", "nibbing", "talking", "ganging", "bonging", "rilling", "damning", "pooling", "porting", "sinning", "collins", "barbing", "bunking", "smiling", "hanging", "tending", "bulging", "ginning", "coiling", "lolling", "molting", "letting", "mending", "hinging", "gunning", "melding", "dilling", "shaving", "harping", "basting", "cobbing", "carting", "leading", "styling", "fowling", "goading", "yowling", "zipping", "wagging", "gaoling", "panning", "valving", "peeling", "titling", "sailing", "harding", "parring", "haloing", "quiting", "punting", "reeling", "batting", "signing", "pegging", "bogging", "mashing", "rankine", "seeding", "sassing", "wafting", "winging", "framing", "rooting", "longing", "sabling", "bulbing", "whiling", "balking", "canting", "dashing", "dueling", "renting", "booting", "whaling", "vatting", "veining", "fencing", "yucking", "slaving", "welling", "sunning", "lulling", "purring", "dawning", "sensing", "meaning", "wording", "hogging", "parsing", "falling", "yelping", "dinning", "vetting", "hulling", "reading", "lapsing", "tooling", "hoaxing", "roiling", "forming", "ramming", "gelding", "felting", "popping", "walling", "costing", "welding", "washing", "filling", "lasting", "couping", "cabling", "getting", "winning", "carping", "martins", "bilking", "burning", "jelling", "sicking", "tinting", "ceiling", "totting", "balding", "kenning", "tinging", "hugging", "westing", "burring", "pasting", "pecking", "parking", "slaying", "pigging", "heating", "manning", "bucking", "bussing", "gagging", "goaling", "rowling", "netting", "funking", "pitying", "jarring", "tasting", "putting", "beating", "funding", "mauling", "balling", "molding", "shining", "perkins", "dialing", "panting", "looping", "welting", "relying", "dulling", "dumping", "tanning", "warring", "gatling", "staging", "finding", "farting", "petting", "picking", "swaying", "toiling", "jambing", "bawling", "minting", "wedding", "hulking", "keeling", "nanking", "railing", "heading", "cutting", "gosling", "vesting", "sighing", "mucking", "copping", "polling", "raising", "fooling", "hooting", "titting", "calming", "seating", "rifling", "soiling", "dubbing", "jesting", "posting", "sacking", "corking", "yipping", "lathing", "bopping", "setting", "coaxing", "poshing", "fawning", "heeling", "warning", "napping", "vending", "mooting", "hurling", "supping", "nanjing", "pipping", "tagging", "mopping", "souping", "palming", "gulling", "kirking", "gilding", "docking", "wefting", "dusting", "sharing", "darling", "bowling", "lauding", "bidding", "hopping", "honking", "hunting", "pepping", "busying", "damming", "patting", "hitting", "gusting", "jigging", "gabbing", "hosting", "sidling", "telling", "rusting", "daubing", "reining", "memling", "healing", "gashing", "betting", "lilting", "hashing", "salving", "firring", "gabling", "ducking", "waiving", "skating", "worming", "waiting", "burying", "booking", "corning", "suiting", "hooking", "gonging", "listing", "hulaing", "sulking", "digging", "fouling", "zincing", "cocking", "packing", "scaling", "pooping", "zinging", "banging", "bolling", "punning", "palling", "sipping", "bunging", "minding", "choking", "yapping", "nicking", "warding", "gorging", "canning", "culling", "lending", "spaying", "lashing", "pupping", "fanning", "banking", "pinging", "roaming", "sopping", "fonding", "searing", "fucking", "rooking", "tooting", "raining", "billing", "pulsing", "curbing", "cashing", "calking", "harking", "tarring", "tacking", "whining", "tarting", "pauline", "rasping", "howling", "helling", "curling", "pucking", "hauling", "coaling", "lopping", "mailing", "wailing", "lugging", "ticking", "staving", "snaking", "selling", "masking", "jabbing", "mewling", "heaving", "soaring", "fagging", "cording", "begging", "ridging", "jetting", "backing", "dotting", "lacking", "parting", "jotting", "dunning", "tinning", "stiling", "stating", "zapping", "hearing", "fitting", "barging", "galling", "wigging", "feeding", "tenting", "looting", "cabbing", "cursing", "dunking", "dabbing", "ragging", "bedding", "witting", "pouting", "burping", "slating", "tamping", "basking", "failing", "papping", "narcing", "lancing", "furlong", "tabling", "dolling", "tailing", "pawning", "collies", "lamming", "coifing", "bolting", "sucking", "rafting", "morning", "ranting", "tabbing", "rinding", "bandung", "bashing", "bending", "ducting", "casting", "camping", "flaming", "hinting", "sanding", "carving", "lagging", "helping", "keening", "jolting", "temping", "junking", "manging", "dimming", "ringing", "tipping", "spiking", "malling", "pursing", "soaking", "willing", "fulling", "causing", "jacking", "furring", "singing", "halting", "tucking", "ruining", "denting", "calling", "barring", "fopping", "yawning", "tilling", "nilling", "downing", "cooling", "martini", "budging", "lapwing", "mincing", "rinsing", "cowling", "marring", "coining", "sibling", "potting", "tauting", "bulling", "lurking", "sorting", "poohing", "bathing", "spicing", "nailing", "spiting", "racking", "lusting", "rutting", "gulping", "tilting", "pairing", "peaking", "capping", "gobbing", "finking");
        pathways = wordLadderII.findLadders(beginWord, endWord, wordList);

        String done = "done";
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return Collections.EMPTY_LIST;
        }
        Map<String, Set<String>> closeWords = new HashMap<>();
        Map<String, Node> graph = new HashMap<>();
        Queue<Node> nodeQueue = new LinkedList<>();
        Node rootNode = new Node(beginWord, null);
        graph.put(beginWord, rootNode);
        nodeQueue.add(rootNode);

        while(!nodeQueue.isEmpty() && (!graph.containsKey(endWord) || graph.get(endWord).level == nodeQueue.peek().level + 1)) {
            Node curNode = nodeQueue.poll();
            for (String closeWord : closeWordsForWord(closeWords, wordList, curNode.word)) {
                if ((!graph.containsKey(closeWord) || graph.get(closeWord).level == curNode.level + 1)){
                    if (!graph.containsKey(closeWord)) {
                        Node newWordNode = new Node(closeWord, curNode);
                        graph.put(closeWord, newWordNode);
                        nodeQueue.add(newWordNode);
                    } else {
                        Node existNode = graph.get(closeWord);
                        if (existNode.level == curNode.level + 1) {
                            existNode.addParent(curNode);
                        }
                    }
                }
            }
        }

        List<List<String>> pathways = graph.containsKey(endWord) ? graph.get(endWord).restorePathwaysToRoot() : Collections.EMPTY_LIST;
        return pathways;
    }

    private Set<String> closeWordsForWord(Map<String, Set<String>> closeWords, List<String> wordList, String word) {
        if (!closeWords.containsKey(word)) {
            closeWords.put(word, new HashSet<>());
               for (String insideWord : wordList) {
                   if (closeWords.containsKey(insideWord) && closeWords.get(insideWord).contains(word)
                        || isCloseWord(word, insideWord)) {
                        closeWords.get(word).add(insideWord);
                   }
               }
        }
        return closeWords.get(word);
    }

    private static boolean isCloseWord(String start, String end) {
        int dif = 0;
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) != end.charAt(i)) {
                dif++;
            }
            if (dif > 1) {
                break;
            }
        }
        return dif == 1;
    }

    private static void addValueToCash(String start, String end,
                                       Map<String, Set<String>> map) {
        Set<String> setStart = map.getOrDefault(start, new HashSet<String>());
        setStart.add(end);
        map.put(start, setStart);
        Set<String> setEnd = map.getOrDefault(end, new HashSet<String>());
        setEnd.add(start);
        map.put(end, setEnd);
    }

    private static class Node {
        String word;
        int level;
        Set<Node> parentNodes;

        public Node(String word, Node parent) {
            this.word = word;
            this.level = parent == null ? 1 : parent.level + 1;
            parentNodes = new HashSet<>();
            if (parent != null) {
                addParent(parent);
            }
        }

        public void addParent(Node parent) {
            if (!parentNodes.contains(parent)) {
                parentNodes.add(parent);
            }
        }

        public List<List<String>> restorePathwaysToRoot() {
            List<List<String>> pathwayList = new LinkedList<>();
            if (parentNodes.isEmpty()) {
                List<String> oneNodePath = new LinkedList<>(Arrays.asList(word));
                pathwayList.add(oneNodePath);
                return pathwayList;
            }
            for (Node parent : parentNodes) {
                pathwayList.addAll(parent.restorePathwaysToRoot());
            }
            for (List<String> pathway : pathwayList) {
                pathway.add(this.word);
            }
            return pathwayList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Node node = (Node) o;

            return word != null ? word.equals(node.word) : node.word == null;
        }

        @Override
        public int hashCode() {
            return word != null ? word.hashCode() : 0;
        }
    }
}
