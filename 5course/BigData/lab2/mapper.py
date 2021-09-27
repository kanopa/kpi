#!/usr/bin/env python

stopwords = ["a", "and", "around", "every", "for", "from", "in",
"is", "it", "not", "on", "one", "the", "to", "under", "of", "at", "are", "as", "or"]

import sys
import re

for line in sys.stdin:
    words = line.split()
    for word in words:
        cleanWord = re.sub('\W+', '', word)
        if (cleanWord in stopwords):
            continue
        print('%s\t%s' % (cleanWord, 1))