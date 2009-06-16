package shm.member {

	[Bindable]
	public class Member {
		public var key:String;
		public var memberId:String;
		public var password:String;
		public var email:String;

		public function toString():String {
			return key;
		}

		public function equals(obj:Object):Boolean {
			if (obj is Member) {
				var m:Member = obj as Member;
				return m.key == this.key;
			}
			return false;
		}
	}
}