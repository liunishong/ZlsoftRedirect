/**CREATE OR REPLACE Function get_zhicall_yygh3012
(
       BeginTradeDate_IN                       Date,
       EndTradeDate_IN                         Date,
       PayNO_IN                                Varchar2:=Null
)Return T_ZHICALL_YYGH3012_TABLE Pipelined
Is
    Cursor C_Balance_Time Is
    Select * From 智康对账记录表 Where TradeDate Between  BeginTradeDate_IN And  EndTradeDate_IN;

    Cursor C_Balance_PayNO Is
    Select * From 智康对账记录表 Where TradeDate Between  BeginTradeDate_IN And  EndTradeDate_IN And PayNO = PayNO_IN;
Begin
    If  nvl(length(PayNO_IN),0) = 0 Then
        For R_Balance In C_Balance_Time Loop
            Pipe Row(
                 T_ZHICALL_YYGH3012_RECORD(to_char(R_Balance.TradeDate,'yyyy-mm-dd hh24:mi:ss'),R_Balance.TradeSource,R_Balance.Channel,R_Balance.MedCardNO
                     ,R_Balance.UserName,R_Balance.RecordType,R_Balance.ServiceType,R_Balance.TradeFee,R_Balance.HisTradeID
                     ,R_Balance.PayNO,R_Balance.RePayNO,R_Balance.TradeID,R_Balance.ThirdSerialNO,R_Balance.RefundType
                 )
            );
        End Loop;
        Return;
    Else
        For R_Balance In C_Balance_PayNO Loop
            Pipe Row(
                 T_ZHICALL_YYGH3012_RECORD(to_char(R_Balance.TradeDate,'yyyy-mm-dd hh24:mi:ss'),R_Balance.TradeSource,R_Balance.Channel,R_Balance.MedCardNO
                     ,R_Balance.UserName,R_Balance.RecordType,R_Balance.ServiceType,R_Balance.TradeFee,R_Balance.HisTradeID
                     ,R_Balance.PayNO,R_Balance.RePayNO,R_Balance.TradeID,R_Balance.ThirdSerialNO,R_Balance.RefundType
                 )
            );
        End Loop;
        Return;
    End If;  
Exception
    When Others Then
        Return ;
End get_zhicall_yygh3012;**/
CREATE OR REPLACE Function Zl_zhicallsplit(p_String Varchar2, p_Separator Varchar2, p_Element Integer)
  Return Varchar2 As 
  --实现VB的Split功能,返回在p_String中以p_Separator为分隔的第p_Element个元素串 
  v_String Varchar2(32767); 
Begin 
  v_String := p_String || p_Separator; 
  For I In 1 .. p_Element - 1 Loop 
    v_String := Substr(v_String, Instr(v_String, p_Separator) + 1); 
  End Loop; 
  Return Substr(v_String, 1, Instr(v_String, p_Separator) - 1); 
Exception 
  When Others Then 
    Return Null; 
End Zl_zhicallsplit;
/

CREATE OR REPLACE Function get_zhicall_yygh3012
(
       BeginTradeDate_IN                       Date,
       EndTradeDate_IN                         Date,
       PayNO_IN                                Varchar2:=Null
)Return T_ZHICALL_YYGH3012_TABLE Pipelined
Is
    Cursor C_Balance_Time Is
    Select * From 智康对账明细 Where 交易时间 Between  BeginTradeDate_IN And  EndTradeDate_IN;

    Cursor C_Balance_PayNO Is
    Select * From 智康对账明细 Where 交易时间 Between  BeginTradeDate_IN And  EndTradeDate_IN And 支付流水号 = PayNO_IN;
Begin
    If  nvl(length(PayNO_IN),0) = 0 Then
        For R_Balance In C_Balance_Time Loop
            Pipe Row(
                 T_ZHICALL_YYGH3012_RECORD(to_char(R_Balance.交易时间,'yyyy-mm-dd hh24:mi:ss'),R_Balance.交易来源,Zl_zhicallsplit(R_Balance.支付方式,'_',1),R_Balance.就诊卡号
                     ,R_Balance.姓名,R_Balance.交易状态,R_Balance.交易类型,to_char(R_Balance.交易金额,'FM999999.00'),R_Balance.his流水号
                     ,R_Balance.支付流水号,R_Balance.退费流水号,R_Balance.业务流水号,R_Balance.三方流水号,R_Balance.退款方式
                 )
            );
        End Loop;
        Return;
    Else
        For R_Balance In C_Balance_PayNO Loop
            Pipe Row(
                 T_ZHICALL_YYGH3012_RECORD(to_char(R_Balance.交易时间,'yyyy-mm-dd hh24:mi:ss'),R_Balance.交易来源,Zl_zhicallsplit(R_Balance.支付方式,'_',1),R_Balance.就诊卡号
                     ,R_Balance.姓名,R_Balance.交易状态,R_Balance.交易类型,to_char(R_Balance.交易金额,'FM999999.00'),R_Balance.his流水号
                     ,R_Balance.支付流水号,R_Balance.退费流水号,R_Balance.业务流水号,R_Balance.三方流水号,R_Balance.退款方式
                 )
            );
        End Loop;
        Return;
    End If;
Exception
    When Others Then
        Return ;
End get_zhicall_yygh3012;
/

