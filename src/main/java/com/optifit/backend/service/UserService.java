package com.optifit.backend.service;

import com.optifit.backend.dto.request.UpdateProfileRequest;
import com.optifit.backend.dto.response.UserProfileResponse;
import com.optifit.backend.exception.ResourceNotFoundException;
import com.optifit.backend.mapper.AppMapper;
import com.optifit.backend.model.User;
import com.optifit.backend.repository.UserRepository;
import com.optifit.backend.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserProfileResponse getCurrentProfile() {
        return AppMapper.toProfile(getCurrentUser());
    }

    @Transactional
    public UserProfileResponse updateCurrentProfile(UpdateProfileRequest request) {
        User user = getCurrentUser();

        if (request.getUsername() != null) {
            user.setUsername(request.getUsername());
        }
        if (request.getAge() != null) {
            user.setAge(request.getAge());
        }
        if (request.getHeight() != null) {
            user.setHeight(request.getHeight());
        }
        if (request.getWeight() != null) {
            user.setWeight(request.getWeight());
        }
        if (request.getGoal() != null) {
            user.setGoal(request.getGoal());
        }

        return AppMapper.toProfile(userRepository.save(user));
    }

    public User getCurrentUser() {
        return userRepository.findByEmail(SecurityUtils.getCurrentUserEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }
}
