import{_ as t}from"./plugin-vue_export-helper.21dcd24c.js";import{r as n,o,c as l,a as e,b as s,e as i,d as a}from"./app.af308e1e.js";const d={},c=i('<h1 id="server-manual" tabindex="-1"><a class="header-anchor" href="#server-manual" aria-hidden="true">#</a> Server Manual</h1><h2 id="environment" tabindex="-1"><a class="header-anchor" href="#environment" aria-hidden="true">#</a> Environment</h2><ul><li>Centos Linux 79</li><li>Macos 10.15.7 +</li><li>Windows 10/11</li></ul><p>With JDK 17.0.5+</p><h2 id="steps" tabindex="-1"><a class="header-anchor" href="#steps" aria-hidden="true">#</a> Steps</h2><ul><li>Centos Linux 7.9 is recommended.</li><li>IntelJ IDEA is recommended since the server use Java language.</li><li>DemoServer is recommended, since it has demoData</li></ul>',6),h=a("Download the latest server release from github, or download the demoServer. "),u={href:"https://github.com/xyliax/GEM-Server/releases",target:"_blank",rel:"noopener noreferrer"},m=a("https://github.com/xyliax/GEM-Server/releases"),_=e("li",null,"Import the Demo data to your mysql database",-1),v=e("li",null,"Prepare Demo data",-1),p=e("li",null,[a("Modify the project configuration file. "),e("ul",null,[e("li",null,"Edit your Database name and password"),e("li",null,"Edit your Workpath"),e("li",null,"In the end use maven to build")])],-1),f=e("li",null,[e("code",null,"java -jar ./GEM-Server-0.0.1-SNAPSHOT.jar")],-1),x=e("p",null,"If necessary, use NGINX to forward the port to 80. don\u2019t forget to change the server address of the client.",-1);function b(E,S){const r=n("ExternalLinkIcon");return o(),l("div",null,[c,e("ol",null,[e("li",null,[h,e("ul",null,[e("li",null,[e("a",u,[m,s(r)])])])]),_,v,p,f]),x])}var D=t(d,[["render",b],["__file","server-manual.html.vue"]]);export{D as default};
