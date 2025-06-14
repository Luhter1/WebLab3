#!/usr/bin/env python3
import sys


def unicode_to_ascii(text):
    result = ''
    for char in text:
        if ord(char) > 127:
            result += '\\u{:04x}'.format(ord(char))
        else:
            result += char
    return result


def i_to_o(input_file, output_file):
    with open(input_file, 'r', encoding='utf-8') as if_content, open(output_file, 'w', encoding='ascii') as of_content:
        s = if_content.read()
        of_content.write(unicode_to_ascii(s))


if __name__ == "__main__":
    i_to_o(sys.argv[1], sys.argv[2])
