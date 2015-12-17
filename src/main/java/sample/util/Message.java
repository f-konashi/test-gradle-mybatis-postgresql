package sample.util;

/**
 * ブラウザ上に表示されるメッセージを一元管理する為のクラスです.
 * 
 * @author f-konashi
 *
 */
public class Message {
	public static final String ERROR_EMPTY = "入力されていません。";
	public static final String ERROR_SELECT = "選択されていません。";
	public static final String ERROR_MINMAX_LENGTH = "5文字以上50文字以内で入力して下さい。";
	public static final String ERROR_MAX_LENGTH = "50文字以内で入力して下さい。";
}

// TODO: enumクラスで、もっとスマートに出来るか検討中

//public enum FormErrorMessage {
//	EMPTY_MESSAGE {
//		@Override
//		public String toString() {
//			return "入力されていません。";
//		}
//	},
//	SELECT_MESSAGE {
//		@Override
//		public String toString() {
//			return "選択されていません。";
//		}
//	},
//	LENGTH_MESSAGE1 {
//		@Override
//		public String toString() {
//			return "50文字以内で入力して下さい。";
//		}
//	},
//	LENGTH_MESSAGE2 {
//		@Override
//		public String toString() {
//			return "50文字以内で入力して下さい。";
//		}
//	}
//}
