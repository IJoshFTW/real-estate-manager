#!/bin/sh

cd ..
git log --pretty=format:user:%aN%n%ct --reverse --raw --encoding=UTF-8 --no-renames >> ../gourcelog.gourcefile.log
gource ../gourcelog.gourcefile.log --load-config ./gource/gource-config.gourcefile.ini
