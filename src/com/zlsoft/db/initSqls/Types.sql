Create Or Replace Type T_ZHICALL_YYGH3012_RECORD As Object(
TradeDate Varchar2(100),
TradeSource Varchar2(100),
Channel Varchar2(100),
MedCardNO Varchar2(100),
UserName Varchar2(100),
RecordType Varchar2(100),
ServiceType Varchar2(100),
TradeFee Varchar2(100),
HisTradeID Varchar2(100),
PayNO Varchar2(100),
RePayNO Varchar2(100),
TradeID Varchar2(100),
ThirdSerialNO Varchar2(100),
RefundType Varchar2(100)
);

Create Or Replace Type T_ZHICALL_YYGH3012_TABLE As Table Of  T_ZHICALL_YYGH3012_RECORD;