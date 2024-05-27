grammar Hello; // 1. grammer name
r  : 'hello' ID ;         // 2. match keyword hello followed by an identifier
ID : [a-z]+ ;             // match lower-case identifiers
WS : [ \t\r\n]+ -> skip ; // 3. skip spaces, tabs, newlines