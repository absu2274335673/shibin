package net.engyne.applepush;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import javapns.devices.Device;
import javapns.devices.implementations.basic.BasicDevice;
import javapns.notification.AppleNotificationServerBasicImpl;
import javapns.notification.PushNotificationManager;
import javapns.notification.PushNotificationPayload;
import javapns.notification.PushedNotification;

public class ApplePush {
	
	public static void pushNotification(String deviceToken,String alert) throws Exception {
	
//		String deviceToken = "93de447ee9f85530ab978d5bea9dfee80133d97ebbd9877f266c9ca22b231389";
//		String alert = "我的push测试";// push的内容
		int badge = 1;// 图标小红圈的数值
		String sound = "default";// 铃音

		List<String> tokens = new ArrayList<String>();
		tokens.add(deviceToken);
		String certificatePath = "PushCertificate.p12";
		String certificatePassword = "taps681(gaff";// 此处注意导出的证书密码不能为空因为空密码会报错
		boolean sendCount = true;

		try {
			PushNotificationPayload payLoad = new PushNotificationPayload();
			payLoad.addAlert(alert); // 消息内容
			payLoad.addBadge(badge); // iphone应用图标上小红圈上的数值
			if (!StringUtils.isBlank(sound)) {
				payLoad.addSound(sound);// 铃音
			}
			PushNotificationManager pushManager = new PushNotificationManager();
			// true：表示的是产品发布推送服务 false：表示的是产品测试推送服务
			pushManager.initializeConnection(new AppleNotificationServerBasicImpl(certificatePath, certificatePassword, false));
			List<PushedNotification> notifications = new ArrayList<PushedNotification>();
			// 发送push消息
			if (sendCount) {
				Device device = new BasicDevice();
				device.setToken(tokens.get(0));
				PushedNotification notification = pushManager.sendNotification(device, payLoad, true);
				notifications.add(notification);
			} else {
				List<Device> devices = new ArrayList<Device>();
				for (String token : tokens) {
					devices.add(new BasicDevice(token));
				}
				notifications = pushManager.sendNotifications(payLoad, devices);
			}
			List<PushedNotification> failedNotifications = PushedNotification.findFailedNotifications(notifications);
			List<PushedNotification> successfulNotifications = PushedNotification.findSuccessfulNotifications(notifications);
			int failed = failedNotifications.size();
			int successful = successfulNotifications.size();
			pushManager.stopConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		pushNotification("d91b9795c54734aa4a5b16b6f69448af302a760fb583f7349f313ff8ade6d9fa","uuu");
	}
}