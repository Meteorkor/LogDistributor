LogDistributor
==============

LogDistributor


한 파일에 모두 저장 되는 로그를 날자를 파싱하여<br>
분산 저장<br>

방식은 일단..

1. File(all) -> Map(Filename,data) -> Files(date)

2. File(all) -> Files(date)

생각보다 속도 차이가 없어서..
(한줄씩 다 파싱하여 분산하기에..)
DB를 사용해볼까 생각중..
(통계내기도 편리할듯..)
File(all) -> DB(all) -> Files(date)
