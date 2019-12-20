package Xokyopo.HW_2.MessageParser;

import java.util.HashMap;
import java.util.Map;

public class MessageParser {
    private String openingText = "<%s>";
    private String closingText = "</%s>";
//    public enum Tag {LOGINTAG, PASSWORDTAG, PRIVATETAG, MESSAGETAG, BLACKLISTTAG, NICKNAMETAG, SERVERCOMANDTAG, ID, CLIENTLISTTAG, SERVERINFOTAG, ERRORTAG, SETNIKNAMETAG};

    public String getValueInStringByTag(String _in, Tag _tag) {
        if (this.isContainsTag(_in, _tag)) {
            return _in.substring(_in.indexOf(this.getOpeningTag(_tag)) + this.getOpeningTag(_tag).length(), _in.indexOf(this.getClosingTag(_tag)));
        }
        return null;
    }

    private String getOpeningTag(Tag _tag) {
        return String.format(this.openingText, _tag);
    }

    private String getClosingTag(Tag _tag) {
        return String.format(this.closingText, _tag);
    }

    public boolean isContainsTag(String _in, Tag _tag) {
        return _in.contains(this.getOpeningTag(_tag)) && _in.contains(this.getClosingTag(_tag));
    }

    private int countingTagInString(String _in, Tag... _tags) {
        int count = 0;
        for (int i = 0; i < _tags.length; i++) {
            count = (this.isContainsTag(_in, _tags[i]))? count + 1: count;
        }
        return count;
    }

    public Map<Tag, String> parseStringByTags(String _in) {
        return this.parseStringByTags(_in, Tag.values());
    }

    public Map<Tag, String> parseStringByTags(String _in, Tag... _tags) {
        if (this.countingTagInString(_in, _tags) > 0) {
            Map<Tag, String> map = new HashMap<>();
            Tag[] tags = Tag.values();
            for (int i = 0; i < tags.length; i++) {
                String value = this.getValueInStringByTag(_in, tags[i]);
                if (value != null) {
                    map.put(tags[i], value);
                }
            }
            return map;
        }
        return null;
    }

    public String buildStringByMap(Map<Tag, String> _map) {
        StringBuilder string = new StringBuilder();
        for (Map.Entry<Tag, String> elemet: _map.entrySet()) {
            string.append(this.buildStringByTag(elemet.getValue(), elemet.getKey()));
        }
        return string.toString();
    }

    public String buildStringByTag(String _in, Tag _tag) {
        return this.getOpeningTag(_tag) + _in + this.getClosingTag(_tag);
    }

    public String[] stringsArrayToArrayOfString(String _string) {
        return _string.substring(1, _string.length() - 1).split(", ");
    }

}
