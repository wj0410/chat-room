<template>
  <div class="chatView">
    <headView :headObj="chatObj.headObj" />
    <div class="chat-message-area">
      <div class="chat-content">
        <div class="message incoming">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <div class="nick-name">WWWWWWW</div>
            <span class="content">你好，有什么问题？</span>
          </div>
        </div>

        <div class="message outgoing">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <span class="content"
              >我想请教一个问题。我想请教一个问题。我想请教一个问题。我想请教一个问题。我想请教一个问题。我想请教一个问题。我想请教一个问题。我想请教一个问题。</span
            >
          </div>
        </div>
        <div class="prompt">12:13 xxx 进入了</div>
        <div class="prompt">12:13 xxx 进入了</div>
        <div class="message outgoing">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <span class="content">我想请教一个问题。</span>
          </div>
        </div>
        <div class="message outgoing">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <span class="content">我想请教一个问题。</span>
          </div>
        </div>
        <div class="message incoming">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <div class="nick-name">WWWWWWW</div>
            <span class="content">[微笑]</span>
          </div>
        </div>
        <div class="message incoming">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <div class="nick-name">WWWWWWW</div>
            <span class="content">[微笑]</span>
          </div>
        </div>
        <div class="message incoming">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <div class="nick-name">WWWWWWW</div>
            <span class="content">[微笑]</span>
          </div>
        </div>
        <div class="message incoming">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <div class="nick-name">WWWWWWW</div>
            <span class="content">[微笑]</span>
          </div>
        </div>
        <div class="message incoming">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <div class="nick-name">WWWWWWW</div>
            <span class="content">[微笑]</span>
          </div>
        </div>
        <div class="message incoming">
          <div class="head-img">
            <img
              class="avatar"
              src="https://img1.baidu.com/it/u=2961575590,2057372040&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500"
            />
          </div>
          <div>
            <div class="nick-name">WWWWWWW</div>
            <span class="content">[微笑]</span>
          </div>
        </div>
      </div>
      <!-- <div class="new-message">
        <svg
          t="1700206635508"
          class="icon"
          viewBox="0 0 1024 1024"
          version="1.1"
          xmlns="http://www.w3.org/2000/svg"
          p-id="2462"
          width="200"
          height="200"
        >
          <path
            d="M500.408996 544.70045c3.684558 3.684558 8.674063 5.757121 13.893853 5.757121 5.21979 0 10.209295-2.072564 13.893853-5.757121L717.567616 355.406297c7.676162-7.676162 7.676162-20.111544 0-27.787706-7.676162-7.676162-20.111544-7.676162-27.787706 0L514.302849 503.018891 333.682759 322.398801c-7.676162-7.676162-20.111544-7.676162-27.787707 0-7.676162 7.676162-7.676162 20.111544 0 27.787706l194.513944 194.513943z m189.370914-59.874063L514.302849 660.303448 333.682759 479.606597c-7.676162-7.676162-20.111544-7.676162-27.787707 0-7.676162 7.676162-7.676162 20.111544 0 27.787706l194.513944 194.513943c3.684558 3.684558 8.674063 5.757121 13.893853 5.757121 5.21979 0 10.209295-2.072564 13.893853-5.757121l189.370914-189.370915c4.989505-4.989505 6.908546-12.205097 5.066267-18.96012-1.842279-6.755022-7.138831-12.051574-13.893853-13.893853-6.755022-1.765517-13.970615 0.153523-18.96012 5.143029z m0 0"
            fill="#03C160"
            p-id="2463"
          ></path>
        </svg>
        <span>1</span>条新消息
      </div> -->
      <!-- <div class="offline">
        <span class="warn">
          <svg
            t="1700553137490"
            class="icon"
            viewBox="0 0 1024 1024"
            version="1.1"
            xmlns="http://www.w3.org/2000/svg"
            p-id="10472"
            width="16"
            height="16"
          >
            <path
              d="M969.182003 701.303054C942.98583 761.818425 907.560805 814.394635 862.909139 859.030351 818.273423 903.682017 765.86402 939.121773 705.727336 965.303215 645.576031 991.515341 581.182839 1004.606061 512.545547 1004.606061 443.925315 1004.606061 379.349482 991.515341 318.818158 965.303215 258.302785 939.121773 205.726577 903.682017 161.090861 859.030351 116.439196 814.393527 80.999438 761.818425 54.817996 701.303054 28.605873 640.772838 15.515152 576.197117 15.515152 507.575665 15.515152 438.938373 28.605873 374.545181 54.817996 314.393878 80.999438 254.257304 116.439196 201.8479 161.090861 157.212073 205.726577 112.560409 258.302785 77.135381 318.818158 50.939208 379.349482 24.743034 443.925203 11.636364 512.545547 11.636364 581.182839 11.636364 645.576031 24.742924 705.727336 50.939208 765.8628 77.1366 818.273311 112.560409 862.909139 157.212073 907.560805 201.848897 942.984611 254.258412 969.182003 314.393878 995.378179 374.545181 1008.484848 438.938373 1008.484848 507.575665 1008.484848 576.197117 995.379287 640.772838 969.182003 701.303054L969.182003 701.303054Z"
              fill="#FF2E00"
              p-id="10473"
            ></path>
            <path
              d="M512 709.220647C472.332325 709.220647 440.203301 741.349668 440.203301 781.017346 440.203301 820.68502 472.332325 852.814044 512 852.814044 551.667675 852.814044 583.796699 820.68502 583.796699 781.017346 583.796699 741.349668 551.667675 709.220647 512 709.220647L512 709.220647Z"
              fill="#FFFFFF"
              p-id="10474"
            ></path>
            <path
              d="M512 639.709091C492.184111 639.709091 476.101651 606.196596 476.101651 564.820837L440.203301 227.823683C440.203301 186.447921 472.332325 152.935427 512 152.935427 551.667675 152.935427 583.796699 186.447921 583.796699 227.823683L547.898349 564.820837C547.898349 606.196596 531.815889 639.709091 512 639.709091L512 639.709091Z"
              fill="#FFFFFF"
              p-id="10475"
            ></path>
          </svg>
        </span>
        <span>当前网络不可用，需检查你的网络设置</span>
      </div> -->
    </div>
    <!-- <div class="chat-send-area">
      <div class="chat-send-tool-area">
        <div class="chat-send-tool-left">
          <ul>
            <li>
              <svg
                id="emoji"
                t="1700469337502"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="30613"
                width="200"
                height="200"
              >
                <path
                  d="M512 959.1c-114.6 0-229.1-43.6-316.3-130.9-174.3-174.4-174.2-458.1 0.2-632.4C298.7 93 445.6 46.6 589 71.5c14.3 2.5 23.9 16.1 21.4 30.4-2.5 14.3-16.1 23.9-30.4 21.4-126.5-22-256.2 19-347 109.7-153.9 153.8-154 404.2-0.2 558.1 153.8 153.9 404.2 154 558.1 0.2s154-404.2 0.2-558.2c-15.4-15.4-32-29.5-49.6-42.1-11.8-8.5-14.5-24.9-6-36.7 8.5-11.8 24.9-14.5 36.7-6 19.8 14.2 38.7 30.3 56.1 47.7 174.3 174.4 174.2 458.1-0.2 632.5-87.1 87-201.6 130.6-316.1 130.6z"
                  p-id="30614"
                ></path>
                <path
                  d="M312.8 410.7m-42.8 0a42.8 42.8 0 1 0 85.6 0 42.8 42.8 0 1 0-85.6 0Z"
                  p-id="30615"
                ></path>
                <path
                  d="M715.6 410.9m-42.5 0a42.5 42.5 0 1 0 85 0 42.5 42.5 0 1 0-85 0Z"
                  p-id="30616"
                ></path>
                <path
                  d="M281.1 553.2v0.4c0 127.5 103.3 231 230.9 231s231-103.3 231-230.9v-0.4l-461.9-0.1z"
                  fill="#FFA786"
                  p-id="30617"
                ></path>
                <path
                  d="M512 810.9s-0.1 0 0 0c-141.9 0-257.3-115.5-257.2-257.3 0-7.9 3.2-26.2 32.7-26.3h82.4c67.1 0 160.4 0 236.9 0.1l133.6 0.1c28.6 0.1 28.9 26 28.9 26.3-0.1 141.7-115.5 257.1-257.3 257.1zM309 579.8c12.9 100.5 99 178.5 202.9 178.5h0.1c103.9 0 190-77.9 203-178.4-82.1 0-324-0.1-406-0.1z"
                  p-id="30618"
                ></path>
                <path
                  d="M673.4 124.4m-26.3 0a26.3 26.3 0 1 0 52.6 0 26.3 26.3 0 1 0-52.6 0Z"
                  fill="#FFA786"
                  p-id="30619"
                ></path>
              </svg>

              <div class="tool-emoji-container">
                <div class="emoji-container">
                  <div class="emoji-content">
                    <ul id="system"></ul>
                  </div>
                  <div class="emoji-page">
                    <span class="emoji-page-dot active"></span>
                    <span class="emoji-page-dot"></span>
                  </div>
                  <div class="emoji-icon">
                    <ul>
                      <li class="choose">
                        <svg
                          t="1700469337502"
                          class="icon"
                          viewBox="0 0 1024 1024"
                          version="1.1"
                          xmlns="http://www.w3.org/2000/svg"
                          p-id="30613"
                          width="200"
                          height="200"
                        >
                          <path
                            d="M512 959.1c-114.6 0-229.1-43.6-316.3-130.9-174.3-174.4-174.2-458.1 0.2-632.4C298.7 93 445.6 46.6 589 71.5c14.3 2.5 23.9 16.1 21.4 30.4-2.5 14.3-16.1 23.9-30.4 21.4-126.5-22-256.2 19-347 109.7-153.9 153.8-154 404.2-0.2 558.1 153.8 153.9 404.2 154 558.1 0.2s154-404.2 0.2-558.2c-15.4-15.4-32-29.5-49.6-42.1-11.8-8.5-14.5-24.9-6-36.7 8.5-11.8 24.9-14.5 36.7-6 19.8 14.2 38.7 30.3 56.1 47.7 174.3 174.4 174.2 458.1-0.2 632.5-87.1 87-201.6 130.6-316.1 130.6z"
                            p-id="30614"
                          ></path>
                          <path
                            d="M312.8 410.7m-42.8 0a42.8 42.8 0 1 0 85.6 0 42.8 42.8 0 1 0-85.6 0Z"
                            p-id="30615"
                          ></path>
                          <path
                            d="M715.6 410.9m-42.5 0a42.5 42.5 0 1 0 85 0 42.5 42.5 0 1 0-85 0Z"
                            p-id="30616"
                          ></path>
                          <path
                            d="M281.1 553.2v0.4c0 127.5 103.3 231 230.9 231s231-103.3 231-230.9v-0.4l-461.9-0.1z"
                            fill="#FFA786"
                            p-id="30617"
                          ></path>
                          <path
                            d="M512 810.9s-0.1 0 0 0c-141.9 0-257.3-115.5-257.2-257.3 0-7.9 3.2-26.2 32.7-26.3h82.4c67.1 0 160.4 0 236.9 0.1l133.6 0.1c28.6 0.1 28.9 26 28.9 26.3-0.1 141.7-115.5 257.1-257.3 257.1zM309 579.8c12.9 100.5 99 178.5 202.9 178.5h0.1c103.9 0 190-77.9 203-178.4-82.1 0-324-0.1-406-0.1z"
                            p-id="30618"
                          ></path>
                          <path
                            d="M673.4 124.4m-26.3 0a26.3 26.3 0 1 0 52.6 0 26.3 26.3 0 1 0-52.6 0Z"
                            fill="#FFA786"
                            p-id="30619"
                          ></path>
                        </svg>
                      </li>
                      <li>
                        <svg
                          t="1701050127513"
                          class="icon"
                          viewBox="0 0 1024 1024"
                          version="1.1"
                          xmlns="http://www.w3.org/2000/svg"
                          p-id="1409"
                          width="200"
                          height="200"
                        >
                          <path
                            d="M512 921.6a64 64 0 0 1-40.32-14.08l-134.826667-106.666667C147.413333 649.813333 69.333333 556.373333 48.64 455.253333a290.986667 290.986667 0 0 1 52.053333-232.533333A279.466667 279.466667 0 0 1 512 183.04a278.4 278.4 0 0 1 188.16-72.32A281.6 281.6 0 0 1 981.333333 391.893333c0 131.2-71.466667 230.186667-293.973333 407.893334l-134.4 106.666666a64 64 0 0 1-40.96 15.146667z m-189.013333-725.333333h-16.213334a190.08 190.08 0 0 0-138.24 77.013333 205.013333 205.013333 0 0 0-36.266666 164.693333c13.226667 64 62.293333 140.16 257.92 295.68L512 830.506667l121.813333-97.28C832 576 896 491.946667 896 391.893333A195.84 195.84 0 0 0 558.933333 256a67.2 67.2 0 0 1-94.08 0 195.413333 195.413333 0 0 0-141.44-59.733333z"
                            fill="#4E4E4E"
                            p-id="1410"
                          ></path>
                          <path
                            d="M213.333333 480a42.666667 42.666667 0 0 1-42.666666-42.666667 202.026667 202.026667 0 0 1 153.813333-196.693333 42.666667 42.666667 0 1 1 21.333333 82.773333A117.12 117.12 0 0 0 256 437.333333a42.666667 42.666667 0 0 1-42.666667 42.666667z"
                            fill="#FF9813"
                            p-id="1411"
                          ></path>
                        </svg>
                      </li>
                    </ul>
                  </div>
                </div>
              </div>
            </li>
            <li>
              <svg
                t="1700477311529"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="3705"
                width="200"
                height="200"
              >
                <path
                  d="M957.867 462.933H76.8c-17.067 0-32-14.933-32-32s14.933-32 32-32h881.067c17.066 0 32 14.934 32 32s-14.934 32-32 32z"
                  fill="#FFBB12"
                  p-id="3706"
                ></path>
                <path
                  d="M817.067 915.2H206.933c-93.866 0-170.666-76.8-170.666-170.667V273.067c0-93.867 76.8-170.667 170.666-170.667h89.6c42.667 0 83.2 17.067 110.934 49.067l76.8 81.066c10.666 10.667 23.466 17.067 38.4 17.067h294.4c93.866 0 170.666 76.8 170.666 170.667v326.4C989.867 838.4 913.067 915.2 817.067 915.2zM206.933 164.267c-59.733 0-106.666 49.066-106.666 106.666V742.4c0 59.733 49.066 106.667 106.666 106.667h610.134c59.733 0 106.666-49.067 106.666-106.667V418.133c0-59.733-49.066-106.666-106.666-106.666h-294.4c-32 0-61.867-12.8-85.334-36.267l-76.8-83.2c-17.066-17.067-40.533-27.733-64-27.733h-89.6z"
                  fill="#543E3E"
                  p-id="3707"
                ></path>
              </svg>
            </li>
            <li>
              <svg
                t="1700477578248"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="15799"
                width="200"
                height="200"
              >
                <path
                  d="M853.333333 896H170.666667a128 128 0 0 1-128-128V256a128 128 0 0 1 128-128h682.666666a128 128 0 0 1 128 128v512a128 128 0 0 1-128 128zM170.666667 213.333333a42.666667 42.666667 0 0 0-42.666667 42.666667v512a42.666667 42.666667 0 0 0 42.666667 42.666667h682.666666a42.666667 42.666667 0 0 0 42.666667-42.666667V256a42.666667 42.666667 0 0 0-42.666667-42.666667z"
                  fill="#4E4E4E"
                  p-id="15800"
                ></path>
                <path
                  d="M282.666667 512m-85.333334 0a85.333333 85.333333 0 1 0 170.666667 0 85.333333 85.333333 0 1 0-170.666667 0Z"
                  fill="#FF9813"
                  p-id="15801"
                ></path>
                <path
                  d="M512 512m-85.333333 0a85.333333 85.333333 0 1 0 170.666666 0 85.333333 85.333333 0 1 0-170.666666 0Z"
                  fill="#FF9813"
                  p-id="15802"
                ></path>
                <path
                  d="M741.333333 512m-85.333333 0a85.333333 85.333333 0 1 0 170.666667 0 85.333333 85.333333 0 1 0-170.666667 0Z"
                  fill="#FF9813"
                  p-id="15803"
                ></path>
              </svg>
            </li>
          </ul>
        </div>
        <div class="chat-send-tool-right">
          <ul>
            <li>
              <svg
                t="1700476796371"
                class="icon"
                viewBox="0 0 1024 1024"
                version="1.1"
                xmlns="http://www.w3.org/2000/svg"
                p-id="3983"
                width="200"
                height="200"
              >
                <path
                  d="M811.52 772.608l-65.536-65.536c-12.8-12.8-34.304-12.8-47.104 0-12.8 12.8-12.8 34.304 0 47.104l65.536 65.536c6.656 6.656 14.848 9.728 23.552 9.728s16.896-3.072 23.552-9.728c12.8-12.8 12.8-33.792 0-47.104zM316.416 283.648L250.88 218.112c-12.8-12.8-34.304-12.8-47.104 0-12.8 12.8-12.8 34.304 0 47.104l65.536 65.536c6.656 6.656 14.848 9.728 23.552 9.728 8.704 0 16.896-3.072 23.552-9.728 13.312-12.8 13.312-33.792 0-47.104z"
                  fill="#FA7268"
                  p-id="3984"
                ></path>
                <path
                  d="M917.504 702.464L814.08 598.528c-38.912-38.912-102.912-38.912-141.824 0l-36.864 36.864c-23.552-9.728-74.24-36.864-142.336-104.96-66.56-66.56-93.696-117.76-103.936-141.824l36.864-36.864c38.912-38.912 38.912-102.912 0-141.824L322.048 107.008c-38.912-38.912-102.912-38.912-141.824 0l-12.8 12.8c-0.512 0.512-1.024 1.536-2.048 2.048-0.512 0.512-1.536 1.024-2.048 2.048-97.28 96.768-113.664 249.856-39.936 364.032 7.68 11.776 14.848 23.552 22.016 36.352 28.672 48.64 64 108.544 155.136 199.68 89.088 89.088 149.504 124.928 198.656 154.112 12.8 7.68 24.576 14.848 36.352 22.016 48.128 31.232 103.424 46.592 158.208 46.592 75.776 0 151.04-29.184 207.36-85.504l1.024-1.024 15.872-15.872c38.912-38.912 38.912-102.912-0.512-141.824z m-345.6 141.824c-12.8-8.192-25.088-15.872-38.4-23.552-48.128-28.672-102.4-60.928-185.344-143.872-84.992-84.992-116.736-138.752-144.896-186.368-7.68-13.312-15.36-26.112-23.552-38.912C122.368 363.52 135.168 245.76 209.92 171.008c0.512-0.512 1.024-1.536 2.048-2.048 0.512-0.512 1.536-1.024 2.048-2.048l12.8-12.8c13.312-13.312 34.816-13.312 47.616 0l103.936 103.936c13.312 13.312 13.312 34.816 0 47.616l-51.2 51.2c-9.728 9.728-12.288 23.552-7.68 35.328 5.12 19.456 29.696 89.6 126.464 185.856 109.056 109.056 181.76 126.464 192 128.512h1.024c2.048 0.512 3.584 0.512 5.632 0.512 1.536 0 3.584 0 5.12-0.512h0.512c1.536-0.512 3.072-0.512 5.12-1.536 0 0 0.512 0 0.512-0.512 1.536-0.512 3.072-1.536 4.608-2.048l0.512-0.512c1.536-1.024 3.072-2.048 4.096-3.072l0.512-0.512c0.512-0.512 1.536-1.024 2.048-2.048l51.712-51.712c13.312-13.312 34.816-13.312 47.616 0l103.936 103.936c13.312 13.312 13.312 34.816 0 47.616l-16.896 16.896-0.512 0.512c-75.264 75.776-193.536 88.064-281.6 30.72z"
                  fill="#3E3A39"
                  p-id="3985"
                ></path>
              </svg>
            </li>
          </ul>
        </div>
      </div>
      <textarea autofocus></textarea>
    </div> -->
  </div>
</template>
<script setup lang="ts">
import { ref } from "vue";
import headView from "../headView.vue";
const props = defineProps({
  chatObj: {
    type: Object,
    default: {},
  },
});
</script>

<style lang="scss" scoped>
.chatView {
  flex: 1;
  min-width: 510px;
  background-color: #f3f3f3;
  display: flex;
  flex-direction: column;
}

.chat-message-area {
  flex: 0.7;
  min-height: 400px;
  position: relative;
  overflow-y: auto;
  overflow-x: hidden;
  display: flex;
  flex-direction: column;
  .chat-content {
    padding: 20px;
    display: flex;
    flex-direction: column;
    overflow-y: auto;
    .message {
      position: relative;
      margin: 5px 0;
      max-width: 60%;
      display: flex;
      font-size: 14px;
      .content {
        margin: 0 10px;
        padding: 10px;
        border-radius: 5px;
        position: relative;
        display: inline-block;
        white-space: pre-line;
        word-break: break-all;
      }
      .nick-name {
        margin: 0 10px 3px;
        color: #adadad;
        font-size: 12px;
      }
    }

    .incoming {
      align-self: flex-start;
      .content {
        background-color: #ffffff;
      }
      .content::before {
        content: "";
        position: absolute;
        left: -15px;
        border-width: 10px;
        border-style: solid;
        border-color: transparent #ffffff transparent transparent;
        top: 20px;
        transform: translateY(-50%);
      }
    }
    .outgoing {
      align-self: flex-end;
      display: flex;
      flex-direction: row-reverse;
      .content {
        background-color: #95ec69;
      }
      .content::before {
        content: "";
        position: absolute;
        right: -15px;
        border-width: 10px;
        border-style: solid;
        border-color: transparent transparent transparent #95ec69;
        top: 20px;
        transform: translateY(-50%);
      }
      .head-img {
        float: right;
      }
    }
    .prompt {
      text-align: center;
      font-size: 13px;
      color: #adadad;
    }
  }
}
.new-message {
  line-height: 0;
  height: 25px;
  background: #ffffff;
  width: 110px;
  font-size: 13px;
  border-top-right-radius: 25px;
  border-bottom-right-radius: 25px;
  border-top-left-radius: 25px;
  border-bottom-left-radius: 25px;
  position: absolute;
  right: 10px;
  bottom: 10px;
  color: #03c160;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  svg {
    height: 23px;
    width: 23px;
  }
}
.offline {
  background-color: #f8e8e8;
  font-size: 12px;
  color: #817374;
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 5px;
  width: 100%;
  position: absolute;
  height: 30px;
  line-height: 1;
}
.warn {
  margin-right: 10px;
  svg {
    width: 18px;
    height: 18px;
  }
}
/* chat-send-area */
.chat-send-area {
  display: flex;
  flex-direction: column;
  flex: 0.3;
  max-height: 350px;
  min-height: 110px;
}
.chat-send-tool-area {
  border-top: 1px solid #ccc;
  display: flex;
}

.chat-send-tool-area li {
  display: inline-block;
  cursor: pointer;
}
.chat-send-tool-left {
  display: flex;
  flex: 1;
}
.chat-send-tool-left li {
  margin: 15px 0 15px 15px;
}
.emoji:focus {
  background-color: red !important;
}
.chat-send-tool-right {
  display: flex;
}
.chat-send-tool-right li {
  margin: 15px 15px 15px 0;
}
.chat-send-area textarea {
  flex: 1;
  padding: 0 15px;
  border: 0;
  resize: none;
  outline: none;
  background-color: #f3f3f3;
}
/* 工具栏 emoji */
.tool-emoji-container {
  position: relative;
}
.tool-emoji-container::before {
  content: "";
  position: absolute;
  right: -3px;
  top: -30px;
  border-width: 15px;
  border-style: solid;
  border-color: #ffffff transparent transparent transparent;
  transform: translateY(-50%);
  z-index: 2;
}
.tool-emoji-container::after {
  content: "";
  position: absolute;
  right: -3px;
  top: -44px;
  border-width: 15px;
  border-style: solid;
  border-color: rgba(0, 0, 0, 0.3) transparent transparent transparent;
  z-index: 1;
}
.emoji-container {
  width: 400px;
  height: 432px;
  background-color: #ffffff;
  position: absolute;
  right: -188px;
  bottom: 44px;
  border-radius: 10px;
  border: 1px solid #ccc; /* 边框样式 */
  box-shadow: 2px 2px 5px #ddd; /* 阴影样式 */
  display: flex;
  flex-direction: column;
  cursor: auto;
}
.emoji-content {
  flex: 1;
  overflow-y: auto;
}
.emoji-content ul {
  display: flex;
  padding: 10px;
  flex-wrap: wrap;
}
.emoji-content li {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 42px;
  width: 42px;
  border-radius: 5px;
  margin: 1.5px 0;
  line-height: 0;
  font-size: 23px;
}
.emoji-content li:hover {
  background-color: #e5e5e5;
}

.emoji-page {
  height: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}
.emoji-page-dot {
  height: 6px;
  width: 6px;
  margin: 0 5px;
  background-color: #e5e5e5;
  border-radius: 50%;
  display: inline-block;
  transition: background-color 0.6s ease;
}
.emoji-page-dot.active {
  background-color: #b2b2b2;
}

.emoji-icon {
  border-top: 1px solid #ccc;
  height: 40px;
  display: flex;
  align-items: center;
}
.emoji-icon svg {
  width: 20px;
  height: 20px;
}
.emoji-icon li {
  margin: 10px;
  padding: 7px;
  line-height: 0;
}
.emoji-icon li.choose {
  background-color: #e5e5e5;
  border-radius: 5px;
}
</style>
