package kakeibo.entity
	[Bindable]
	[RemoteClass(alias="kakeibo.entity.Category")]
	{
		public var id:Number;
		public var name:String;
		public var version:Number;
		public var subcategoryList:ArrayCollection;
	}
}