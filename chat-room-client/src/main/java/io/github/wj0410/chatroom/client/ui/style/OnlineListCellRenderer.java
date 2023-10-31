package io.github.wj0410.chatroom.client.ui.style;

import io.github.wj0410.chatroom.client.holder.ClientHolder;
import io.github.wj0410.chatroom.client.ui.ChatRoomUI;
import io.github.wj0410.chatroom.client.ui.model.OnlineModel;
import io.github.wj0410.chatroom.client.util.ClientUtil;
import io.github.wj0410.chatroom.common.model.ClientModel;
import io.github.wj0410.chatroom.common.util.UIUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author wangjie
 */
public class OnlineListCellRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index,
                                                  boolean isSelected, boolean cellHasFocus) {
        //使用父类的list cell renderer设置基本属性
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        //获取客户端模型对象
        OnlineModel onlineModel = (OnlineModel) value;
        //设置文本
        String userName = ClientUtil.formatClientAccount(onlineModel);
        String fontColor = "";
        //判断自己还是其他人
        if (onlineModel.getClientId().equals(ClientHolder.clientInfo.getClientId())) {
            //如果是自己，设置字体为绿色
            fontColor = "#339966";
            // 判断是否选中
            if (isSelected) {
                // 设置选中时的背景色为浅蓝色
                setBackground(Color.WHITE);
            }
        } else {
            //如果是其他人，则设置颜色为默认颜色
            fontColor = "#000000";
            // 判断是否选中
            if (isSelected) {
                // 设置选中时的背景色为灰色
                setBackground(UIUtil.GREY_COLOR);
            } else {
                // 设置未选中时的背景色为白色
                setBackground(Color.WHITE);
            }
        }
        String formatStr = "<html><font color='%s'>%s</font><font color='#FF0000'>%s</font></html>";
        String htmlText = "";
        int unreadCount = onlineModel.getUnreadCount();
        if (unreadCount > 0) {
            htmlText = String.format(formatStr, fontColor, userName, unreadCount);
        } else {
            htmlText = String.format(formatStr, fontColor, userName, "");
        }
        setText(htmlText);
        return this;
    }
}