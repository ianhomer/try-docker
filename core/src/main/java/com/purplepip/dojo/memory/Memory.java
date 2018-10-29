/*
 * Copyright (c) 2017 the original author or authors. All Rights Reserved
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.purplepip.dojo.memory;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;

/**
 * A cheap and cheerful memory dump.
 */
public class Memory {
  public String getSummary() {
    Runtime runtime = Runtime.getRuntime();
    StringBuilder sb = new StringBuilder(128);
    sb.append("T=").append(bytesToKb(runtime.totalMemory()));
    for (MemoryPoolMXBean bean : ManagementFactory.getMemoryPoolMXBeans()) {
      long used = bean.getUsage().getUsed();
      sb.append(";").append(bean.getName(), 0, 2).append("=").append(bytesToKb(used));
    }
    return sb.toString();
  }

  private long bytesToKb(long bytes) {
    return bytes / 1024;
  }
}
