def solution(participant, completion):
    answer = ''
    
    participant.sort()
    completion.sort()
    
    for par,com in zip(participant,completion):
        if par!=com:
            return par
    return participant[-1]

    #효율성테스트X
    # for c in completion:
    #     if c in participant:
    #         participant.remove(c)
