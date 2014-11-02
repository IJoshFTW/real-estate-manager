.\fetchGravatarsForGource.pl
cd ..
cd ..
New-Item -ItemType Directory -Force -Path ./gource/logging | Out-Null
git log --pretty=format:user:%aN%n%ct --reverse --raw --encoding=UTF-8 --no-renames | Out-File -Encoding UTF7 ./gource/logging/gourcelog.log
gource ./gource/logging/gourcelog.log --load-config ./gource/config/gource-config.ini
Exit