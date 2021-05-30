# JSON - XML converter

## Stage 1/6: Elementary_

**Conversion of XML to JSON:**

Extract an element between tags and enclose it within double quotes.  
Extract element content and enclose it within double quotes. If the element is empty, assign null (JSON value).

**Conversion of JSON to XML:**

Extract key between double quotes (XML element).  
Extract element content from JSON value.  
If value is not null, enclose JSON with  <></>, else enclose within  < />.

Write a program that reads XML/JSON from standard input and outputs the converted XML/JSON.