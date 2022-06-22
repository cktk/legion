#!/bin/bash

cd /Users/leiyanfu/IdeaProjects/我的/脚手架/单体框架/legion-ui || exit
npm run build:docker
cd /Users/leiyanfu/IdeaProjects/我的/脚手架/单体框架/legion/legion-monitor || exit
mvnd clean
mvnd package
cd /Users/leiyanfu/IdeaProjects/我的/脚手架/单体框架/legion || exit
mvnd clean
mvnd package
docker-compose  build
