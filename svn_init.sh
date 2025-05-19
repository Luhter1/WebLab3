#!/bin/bash
# set -x; set -v

rm -rf svn
mkdir svn
cd svn

REMOTE_URL="file://$(pwd -P)/repo"

svnadmin create repo
svn mkdir $REMOTE_URL/trunk $REMOTE_URL/branches -m "File structure"

# Создание рабочей копии
svn checkout $REMOTE_URL/trunk working_copy
cd working_copy