require 'yaml'

pubspec = YAML.load_file(File.join('..', 'pubspec.yaml'))
library_version = pubspec['version'].gsub('+', '-')

Pod::Spec.new do |s|
  s.name             = 'app_badge_plus'
  s.version          = library_version
  s.summary          = 'App badge plugin for Flutter.'
  s.description      = <<-DESC
App badge plugin for Flutter.
                       DESC
  s.homepage         = 'https://github.com/windows7lake/app_badge_plus'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Lio lin' => 'windows7lake@gmail.com' }

  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'

  s.resource_bundles = {'app_badge_plus_privacy' => ['Resources/PrivacyInfo.xcprivacy']}

  s.dependency 'Flutter'

  s.platform = :ios, '11.0'
  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end
