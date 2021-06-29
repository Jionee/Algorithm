from collections import defaultdict
from operator import itemgetter

def solution(genres, plays):
    answer = []
    #zip : 동일한 개수를 가진 자료형을 묶어주는 것
    
    #어떤 장르가 가장 많은지 판단
    #dictionary 가 중복은 허용하지 않으므로 반복해서 더하는 것으로 해결
    genre_play_dict = defaultdict(lambda:0)
    for genre,play in zip(genres,plays):
        genre_play_dict[genre]+=play
    #print(genre_play_dict)
    
    #장르 랭킹
    genre_rank = [genre for genre,play in sorted(genre_play_dict.items(), key=itemgetter(1),reverse=True)]
    
    #key,value값 가진 dict 만들기
    final_dict = defaultdict(lambda:[])
    for i,genre_play_tuple in enumerate (zip(genres,plays)):
        final_dict[genre_play_tuple[0]].append((genre_play_tuple[1],i))
    #print(final_dict)
    
    #장르별 랭킹
    for genre in genre_rank:
        one_genre_list = sorted(final_dict[genre],key = itemgetter(0),reverse=True)
        if len(one_genre_list) > 1:
            answer.append(one_genre_list[0][1])
            answer.append(one_genre_list[1][1])
        else:
            answer.append(one_genre_list[0][1])
   
    return answer
