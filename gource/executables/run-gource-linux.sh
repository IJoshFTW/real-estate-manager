#!/bin/sh

fetchGravatarsForGource.pl
cd ..
cd ..
git log --pretty=format:user:%aN%n%ct --reverse --raw --encoding=UTF-8 --no-renames >> ./gource/logging/gourcelog.log
gource ./gource/logging/gourcelog.log --load-config ./gource/config/gource-config.ini
