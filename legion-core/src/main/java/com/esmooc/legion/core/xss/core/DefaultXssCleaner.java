/*
 * Copyright (c) 2019-2029, Dreamlu 卢春梦 (596392912@qq.com & www.dreamlu.net).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.esmooc.legion.core.xss.core;

import cn.hutool.core.util.CharsetUtil;
import com.esmooc.legion.core.xss.config.XssProperties;
import com.esmooc.legion.core.xss.utils.XssUtil;
import jodd.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities;
import org.jsoup.safety.Cleaner;
import org.springframework.web.util.HtmlUtils;


/**
 * 默认的 xss 清理器
 *
 * @author L.cm
 */
@RequiredArgsConstructor
public class DefaultXssCleaner implements XssCleaner {

	private final XssProperties properties;

	@Override
	public String clean(String bodyHtml) {
		// 1. 为空直接返回
		if (StringUtil.isBlank(bodyHtml)) {
			return bodyHtml;
		}
		XssProperties.Mode mode = properties.getMode();
		if (XssProperties.Mode.escape == mode) {
			// html 转义
			return HtmlUtils.htmlEscape(bodyHtml, CharsetUtil.UTF_8);
		}
		else {
			// jsoup html 清理
			Document.OutputSettings outputSettings = new Document.OutputSettings()
					// 2. 转义，没找到关闭的方法，目前这个规则最少
					.escapeMode(Entities.EscapeMode.xhtml)
					// 3. 保留换行
					.prettyPrint(properties.isPrettyPrint());
			Document dirty = Jsoup.parseBodyFragment(bodyHtml, "");
			Cleaner cleaner = new Cleaner(XssUtil.WHITE_LIST);
			Document clean = cleaner.clean(dirty);
			clean.outputSettings(outputSettings);
			// 4. 清理后的 html
			String escapedHtml = clean.body().html();
			if (properties.isEnableEscape()) {
				return escapedHtml;
			}
			// 5. 反转义
			return Entities.unescape(escapedHtml);
		}
	}

}
